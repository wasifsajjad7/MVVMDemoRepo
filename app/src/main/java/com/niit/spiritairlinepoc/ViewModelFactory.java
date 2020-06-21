package com.niit.spiritairlinepoc;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.ui.login.LoginViewModel;
import com.niit.spiritairlinepoc.ui.main.MainViewModel;
import com.niit.spiritairlinepoc.ui.splash.SplashViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DataManager mDataManager;

    public ViewModelFactory(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mDataManager);
        }else if(modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mDataManager);
        }else if(modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(mDataManager);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
