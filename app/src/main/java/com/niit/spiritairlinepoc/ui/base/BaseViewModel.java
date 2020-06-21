package com.niit.spiritairlinepoc.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.niit.spiritairlinepoc.data.DataManager;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager= dataManager;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    private WeakReference<N> mNavigator;

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

}
