
package jp.mixi.assignment.contentprovider.beg;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mSimpleCursorAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:独自ContentProviderを作成してください
        // TODO:データベースにinsert、queryができるようにしてください
        mListView = (ListView) findViewById(R.id.listView1);
        // UIにバインドするデータのカラム名
        String[] from = {
                Drink.COLUMN_NAME_DRINK_NAME, Drink.COLUMN_NAME_DRINK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Name, R.id.Price
        };

        // 第3引数のCursorはコールバックで設定されるのでnullを渡しています
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_drink, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);
        
        // ローダの管理をするオブジェクト
        LoaderManager loaderManager = getSupportLoaderManager();
        // ローダを初期化して非同期処理を開始する
        loaderManager.initLoader(0, null, this);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }
    
    private void insert() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Drink.COLUMN_NAME_DRINK_NAME, "NAME" + i);
            values.put(Drink.COLUMN_NAME_DRINK_PRICE, "PRICE" + i);

            Uri insert = getContentResolver().insert(Drink.CONTENT_URI, values);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        return new CursorLoader(this, Drink.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor c) {
        mSimpleCursorAdapter.swapCursor(c);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> c) {
        mSimpleCursorAdapter.swapCursor(null);
	}

}
