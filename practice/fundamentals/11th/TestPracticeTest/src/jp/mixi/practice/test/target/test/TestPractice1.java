package jp.mixi.practice.test.target.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import jp.mixi.practice.test.target.SubActivity;
import jp.mixi.practice.test.target.TestTarget1;

public class TestPractice1 extends AndroidTestCase {

	public void testStartSubActivity() throws Exception {
		TestTarget1 target = new TestTarget1();
		target.startSubActivity(new TestTarget1Context(getContext()), "hogehoge");
		
	}
	
	public void testIsValidIntent() throws Exception {
		TestTarget1 target = new TestTarget1();
		
		Uri uri = Uri.parse("http://mixi.jp/");
		Intent i1 = new Intent();
		Intent i2 = new Intent();
		Intent i3 = new Intent();
		i1.setData(uri);
		i1.putExtra(Intent.EXTRA_TEXT, "extra");
		i2.putExtra(Intent.EXTRA_TEXT, "extra");
		i3.setData(uri);
		i3.putExtra(Intent.EXTRA_SUBJECT, "subject");
		
		assertTrue(target.isValidIntent(i1));
		assertFalse(target.isValidIntent(i2));
		assertFalse(target.isValidIntent(i3));
	}
	
	private static class TestTarget1Context extends MockContext {
		private Context mContext;
		
		public TestTarget1Context(Context baseContext) {
			mContext = baseContext;
		}
		
        @Override
        public String getPackageName() {
            return mContext.getPackageName();
        }
        
        @Override
        public void startActivity(Intent i) {
        	ComponentName component = i.getComponent();
        	assertEquals(SubActivity.class.getCanonicalName(), component.getClassName());
        	assertTrue(i.hasExtra(Intent.EXTRA_SUBJECT));
        	assertEquals("hogehoge", i.getStringExtra(Intent.EXTRA_SUBJECT));
        }
	}
}
