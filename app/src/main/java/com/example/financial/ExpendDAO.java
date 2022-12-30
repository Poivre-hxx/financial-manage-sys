package com.example.financial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpendDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Expend expend);

    @Query("DELETE FROM expend_table")
    void deleteAll();

    @Query("SELECT * FROM expend_table ORDER BY expend ASC")
    LiveData<List<Expend>> getAllExpends();

    @Query("SELECT * FROM expend_table LIMIT 1")
    Expend[] getAnyExpend();

    @Delete
    void deleteExpend(Expend expend);
}
