package com.example.financial;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpendViewModel extends AndroidViewModel {
    private ExpendRepository mRepository;
    private LiveData<List<Expend>> mAllExpends;

    public ExpendViewModel(Application application) {
        super(application);
        mRepository = new ExpendRepository(application);
        mAllExpends = mRepository.getAllExpends();
    }

    public LiveData<List<Expend>> getAllExpends() {
        return mAllExpends;
    }

    public void insert(Expend expend) {
        mRepository.insert(expend);
    }
    public void deleteAll() {
        mRepository.deleteAll();
    }
    public void deleteExpend(Expend expend) {
        mRepository.deleteExpend(expend);
    }
}
