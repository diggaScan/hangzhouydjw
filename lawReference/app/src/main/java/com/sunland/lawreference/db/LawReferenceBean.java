package com.sunland.lawreference.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "lawReferences")
public class LawReferenceBean {

    @ColumnInfo
    @PrimaryKey
    @NonNull
    public String title;

    @ColumnInfo
    public float timeStamp;

    @ColumnInfo
    public String filename;

    @ColumnInfo
    public int image;

}
