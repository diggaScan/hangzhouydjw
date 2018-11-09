package com.sunland.lawreference;

import com.sunland.lawreference.crash.CrashApplication;
import com.sunland.lawreference.db.MdbOpenHelper;

public class MyApplication extends CrashApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MdbOpenHelper.createDb(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        MdbOpenHelper.closeDb();
    }
}
