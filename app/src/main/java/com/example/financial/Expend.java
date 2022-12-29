package com.example.financial;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expend_table")
public class Expend {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "expend")
    private String mExpend;

    public Expend(@NonNull String expend) {
        this.mExpend = expend;
    }

    public String getExpend() {
        return this.mExpend;
    }
}