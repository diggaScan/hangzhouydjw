package com.sunland.lawreference.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LawReferenceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LawReferenceBean lawReferenceBean);

    @Delete
    void delete(LawReferenceBean lawReferenceBean);

    @Query("SELECT * FROM lawReferences")
    List<LawReferenceBean> getALlCollect();

    @Query("SELECT title FROM lawReferences")
    List<String> getAllTitles();

    @Query("SELECT * FROM lawReferences ORDER BY timeStamp desc")
    List<LawReferenceBean> loadAllLawRefersDesc();

    @Query("SELECT * FROM lawReferences WHERE title=:file_name")
    List<LawReferenceBean> getRecordByTitle(String file_name);

}
