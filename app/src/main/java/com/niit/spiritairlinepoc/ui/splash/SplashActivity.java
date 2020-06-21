package com.niit.spiritairlinepoc.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.niit.spiritairlinepoc.R;
import com.niit.spiritairlinepoc.ViewModelFactory;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.databinding.ActivitySplashBinding;
import com.niit.spiritairlinepoc.ui.base.BaseActivity;
import com.niit.spiritairlinepoc.ui.login.LoginActivity;
import com.niit.spiritairlinepoc.ui.main.MainActivity;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {


    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return com.niit.spiritairlinepoc.BR.LoginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startSeeding();
    }

    @Override
    public SplashViewModel getViewModel() {
        ViewModelFactory factory = new ViewModelFactory(DataManager.getInstance());
        mSplashViewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
