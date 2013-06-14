
package jp.mixi.assignment.sqlite.beg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlacticeOpenHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = PlacticeOpenHelper.class.getSimpleName();

    // データーベースのバージョン
    // データベーススキーマを変える場合は、バージョンを上げること
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "plactice.db";

    private static final String PLACTICE_TABLE_CREATE =
            "CREATE TABLE " + Plactice.PLACTICE_TABLE_NAME + " (" +
            		Plactice._ID + " INTEGER PRIMARY KEY," +
            		Plactice.COLUMN_NAME_PLACTICE_NAME + " TEXT NOT NULL, " +
            		Plactice.COLUMN_NAME_PLACTICE_VERSION + " TEXT);";

    private static final String PLACTICE_TABLE_DELETE =
            "DROP TABLE IF EXISTS " +Plactice.PLACTICE_TABLE_NAME;

    public PlacticeOpenHelper(Context context) {
        // データベース名、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(PLACTICE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(PLACTICE_TABLE_DELETE);
        onCreate(db);
    }

}
