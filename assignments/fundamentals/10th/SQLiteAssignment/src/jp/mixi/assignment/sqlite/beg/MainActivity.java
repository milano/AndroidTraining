
package jp.mixi.assignment.sqlite.beg;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	PlacticeOpenHelper placticeOpenHelper = new PlacticeOpenHelper(this);

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }
	
    private void insert() {
        // 書き込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = placticeOpenHelper.getWritableDatabase();
        
        EditText title = (EditText) findViewById(R.id.editText1);
        EditText version = (EditText) findViewById(R.id.editText2);
        
        ContentValues values = new ContentValues();
        values.put(Plactice.COLUMN_NAME_PLACTICE_NAME, title.getText().toString());
        values.put(Plactice.COLUMN_NAME_PLACTICE_VERSION, version.getText().toString());

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        long rowId = db.insert(Plactice.PLACTICE_TABLE_NAME, null, values);
        if (rowId != -1) {
        	Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
        }
    }

    private void query() {
    	// 読み込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = placticeOpenHelper.getReadableDatabase();

        ListView listView = (ListView) findViewById(R.id.listView1);

        // 取得する情報を指定
        String[] projection = {
                Plactice._ID,
                Plactice.COLUMN_NAME_PLACTICE_NAME,
                Plactice.COLUMN_NAME_PLACTICE_VERSION
        };

        // 条件を指定
        Cursor cursor = db.query(Plactice.PLACTICE_TABLE_NAME, projection, null, null, null, null, null);
        // UIにバインドするデータのカラム名
        String[] from = {
        		Plactice.COLUMN_NAME_PLACTICE_NAME, Plactice.COLUMN_NAME_PLACTICE_VERSION
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Name, R.id.Version
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item_practice, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
