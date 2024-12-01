package com.example.videoapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CodeEntryDao {
    @Insert
    void insert(CodeEntry codeEntry);

    @Query("SELECT COUNT(*) FROM code_entries")
    LiveData<Integer> getTotalCount();
}
