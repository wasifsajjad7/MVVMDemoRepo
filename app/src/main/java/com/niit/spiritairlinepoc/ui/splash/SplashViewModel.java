

package com.niit.spiritairlinepoc.ui.splash;

import android.os.Handler;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.ui.base.BaseViewModel;


public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void startSeeding() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                decideNextActivity();
            }
        },5000);
    }

    private void decideNextActivity() {
        if (getDataManager().getSharedPreference().getAccessToken() == null) {
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openMainActivity();
        }
    }
}
