package com.sunland.lawreference.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = LawReferenceBean.class, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract LawReferenceDao getLawRefDao();
}
