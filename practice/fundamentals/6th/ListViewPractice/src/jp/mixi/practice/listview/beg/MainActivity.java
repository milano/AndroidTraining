
package jp.mixi.practice.listview.beg;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewに表示するデータを作成する
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("hoge" + i);
        }
        
        final ListView listView = (ListView) findViewById(R.id.ListView);
        
//		// android.R.layout.simple_list_item_1はAndroidで既に定義されているリストアイテムのレイアウトです
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//		        android.R.layout.simple_list_item_1, list);
//		listView.setAdapter(adapter);
		listView.setAdapter(new CustomListItemAdapter(this, list));
		
		// タップした時の動作を定義する
		listView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		        // Adapterからタップした位置のデータを取得する
		        String str = (String) parent.getItemAtPosition(position);
		        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
		    }
		});
		
		Button button1 = (Button) findViewById(R.id.Button1);
		button1.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				listView.smoothScrollToPosition(0);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
