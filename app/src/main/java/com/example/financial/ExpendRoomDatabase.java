package com.example.financial;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Expend.class}, version = 1, exportSchema = false)
public abstract class ExpendRoomDatabase extends RoomDatabase {

    public abstract ExpendDAO getExpendDAO();

    private static ExpendRoomDatabase INSTANCE;

    public static ExpendRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ExpendRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpendRoomDatabase.class, "expend_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ExpendDAO mDao;
        String [] expends = {
                "麦当当 餐饮 9.99 12",
                "肯爷爷 餐饮 9.99 12",
                "外套 服饰 1299.00 12",
                "鞋子 服饰 399.00 12",
                "gibson 乐器 5999.00 12"
        };

        PopulateDbAsync(ExpendRoomDatabase db) {
            mDao = db.getExpendDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            if(mDao.getAnyExpend().length < 1) {
                for(int i = 0; i <= expends.length - 1; i++) {
                    Expend expend = new Expend(expends[i]);
                    mDao.insert(expend);
                }
            }
            return null;
        }
    }
}
