
package jp.mixi.assignment.sqlite.beg;

import android.provider.BaseColumns;

public class Plactice implements BaseColumns {

    @SuppressWarnings("unused")
    private static final String TAG = Plactice.class.getSimpleName();

    public static final String PLACTICE_TABLE_NAME = "android_code_name";

    public static final String COLUMN_NAME_PLACTICE_NAME = "name";
    public static final String COLUMN_NAME_PLACTICE_VERSION = "version";

    private String mName;
    private String mVersion;

    public Plactice(String name, String version) {
        super();
        mName = name;
        mVersion = version;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

}
