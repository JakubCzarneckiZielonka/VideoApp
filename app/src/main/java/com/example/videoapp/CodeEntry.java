package com.example.videoapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "code_entries")
public class CodeEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int count;

    public CodeEntry(int count) {
        this.count = count;
    }
}

