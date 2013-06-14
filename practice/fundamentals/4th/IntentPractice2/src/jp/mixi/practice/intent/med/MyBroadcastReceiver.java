package jp.mixi.practice.intent.med;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	String action = intent.getAction();
    	Log.v("hoge", action);
    }
}