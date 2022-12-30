package com.example.financial;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpendRepository {
    private ExpendDAO mExpendDAO;
    private LiveData<List<Expend>> mAllExpends;

    ExpendRepository(Application application) {
        ExpendRoomDatabase db = ExpendRoomDatabase.getDatabase(application);
        mExpendDAO = db.getExpendDAO();
        mAllExpends = mExpendDAO.getAllExpends();
    }

    LiveData<List<Expend>> getAllExpends() {
        return mAllExpends;
    }

    public void insert(Expend expend) {
        new insertAsyncTask(mExpendDAO).execute(expend);
    }
    public void deleteAll() {
        new deleteAllExpendsAsyncTask(mExpendDAO).execute();
    }
    public void deleteExpend(Expend expend) {
        new deleteExpendAsyncTask(mExpendDAO).execute(expend);
    }

    public static class insertAsyncTask extends AsyncTask<Expend, Void, Void> {
        private ExpendDAO mAsyncTaskDao;

        insertAsyncTask(ExpendDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expend... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public static class deleteAllExpendsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExpendDAO mAsyncTaskDao;

        deleteAllExpendsAsyncTask(ExpendDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public static class deleteExpendAsyncTask extends AsyncTask<Expend, Void, Void> {
        private ExpendDAO mAsyncTaskDao;

        deleteExpendAsyncTask(ExpendDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expend... params) {
            mAsyncTaskDao.deleteExpend(params[0]);
            return null;
        }
    }
}
