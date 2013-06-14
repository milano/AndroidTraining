package jp.mixi.practice.network.networkpractice1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
            	EditText accessUrl = (EditText) findViewById(R.id.accessUrl);
            	new MyAsyncTask().execute("GET", accessUrl.getText().toString());
            }
        });
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
            	EditText accessUrl = (EditText) findViewById(R.id.accessUrl);
            	EditText httpBody  = (EditText) findViewById(R.id.httpBody);
            	new MyAsyncTask().execute("POST", accessUrl.getText().toString(), httpBody.getText().toString());
            }
        });
    }

    /**
     * 非同期処理を実行するためのネストクラス。
     * Activity などのライフサイクルに合わせた管理は自分でする必要があるが、
     * この例では特にしていないので、Activity が GC されると良くないことが起こる。
     *
     * ジェネリクスの仕組みを用いて、非同期処理に渡す引数の型、進捗を監視するコールバック用の型、非同期処理の結果を表す型を指定する。
     *
     * @author keishin.yokomaku
     */
    private class MyAsyncTask extends AsyncTask<String, String, String> {
        /**
         * 非同期処理を実行する前に UI スレッドで実行する処理を書く
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "onPreExecute", Toast.LENGTH_SHORT).show();
        }

        /**
         * 非同期処理の進捗を受け取るコールバック。
         */
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 非同期処理の本体で、UI スレッドではない別のスレッドで処理する内容。
         * 引数は非同期処理内容に渡すためのパラメータの配列。
         */
        @Override
        protected String doInBackground(String... params) {
        	String result = null;
        	if (params[0].equals("GET")) {
                URL url = null;
                StringBuilder src = new StringBuilder();
				try {
					url = new URL(params[1]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream is = connection.getInputStream();

                    while (true) {
                        byte[] line = new byte[1024];
                        int size = is.read(line);
                        if (size <= 0)
                            break;
                        src.append(new String(line, "euc-jp"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    connection.disconnect();
                }
                result = src.toString();
        	} else if (params[0].equals("POST")) {
                URL url = null;
                StringBuilder src = new StringBuilder();
				try {
					url = new URL(params[1]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    String postData = params[2];
                    OutputStream os = connection.getOutputStream();
                    os.write(postData.getBytes());
                    os.flush();
                    os.close();

                    InputStream is = connection.getInputStream();

                    while (true) {
                        byte[] line = new byte[1024];
                        int size = is.read(line);
                        if (size <= 0)
                            break;
                        src.append(new String(line, "euc-jp"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    connection.disconnect();
                    TextView response = (TextView) findViewById(R.id.response);
                    response.setText(src);
                }
                result = src.toString();
        	}
        	return result;
        }

        /**
         * 非同期処理の実行後に、UI スレッドで実行する処理。
         * 引数は {@link AsyncTask#execute(Object...)} の返り値。
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TextView response = (TextView) findViewById(R.id.response);
            response.setText(result);
        }
    }
}
