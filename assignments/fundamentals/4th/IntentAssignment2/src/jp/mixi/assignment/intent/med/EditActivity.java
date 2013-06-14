package jp.mixi.assignment.intent.med;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class EditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        findViewById(R.id.Submit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここで、id が MyEdit の EditText からテキストを取得して、結果にセットする
            	EditText editText = (EditText) findViewById(R.id.MyEdit);
            	String text = editText.getText().toString();
            	
            	Intent i = new Intent();
            	i.putExtra("toast_message", text);
            	setResult(RESULT_OK, i);
            	finish();
            }
        });
    }
}