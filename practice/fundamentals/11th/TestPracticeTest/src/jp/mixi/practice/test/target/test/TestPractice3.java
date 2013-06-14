package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.R;
import jp.mixi.practice.test.target.TestTarget3;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class TestPractice3 extends ActivityInstrumentationTestCase2<TestTarget3>{
	public TestPractice3() {
		super(TestTarget3.class);
	}

	public TestPractice3(Class<TestTarget3> activityClass) {
		super(activityClass);
	}
	
	public void testCountSenario() throws Exception {
		Activity activity = getActivity();
		
        final TextView title = (TextView) activity.findViewById(R.id.TitleEditor);
        final TextView content = (TextView) activity.findViewById(R.id.ContentEditor);
        TextView titleCounter = (TextView) activity.findViewById(R.id.TitleCounter);
        TextView contentCounter = (TextView) activity.findViewById(R.id.ContentCounter);

        // 最初は空白
        assertEquals("", titleCounter.getText().toString());
        assertEquals("", contentCounter.getText().toString());
        
        // 文字を入力するとカウンターが表示される
        activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
	            title.setText("1");
			}
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("1 / 10", titleCounter.getText().toString());
        assertEquals("0 / 10000", contentCounter.getText().toString());
        
        activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				content.setText("1");
			}
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("1 / 10000", contentCounter.getText().toString());

    	// 文字を消してもカウンターは消えない
        activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
	            title.setText("");
			}
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("0 / 10", titleCounter.getText().toString());
        assertEquals("1 / 10000", contentCounter.getText().toString());
        
        activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				content.setText("");
			}
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("0 / 10000", contentCounter.getText().toString());
	}
}
