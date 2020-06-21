package com.niit.spiritairlinepoc.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.niit.spiritairlinepoc.ViewModelFactory;
import com.niit.spiritairlinepoc.R;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.data.network.model.UserDetails;
import com.niit.spiritairlinepoc.databinding.ActivityMainBinding;
import com.niit.spiritairlinepoc.ui.base.BaseActivity;
import com.niit.spiritairlinepoc.ui.login.LoginActivity;
import com.niit.spiritairlinepoc.utils.AppValidationMassages;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> implements UserDetailsAdapter.UserDetailsListener,MainScreenNavigator {


    private ActivityMainBinding mActivityMainBinding;

    private MainViewModel mMainViewModel;

    private UserDetailsAdapter userDetailsAdapter;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return com.niit.spiritairlinepoc.BR.MainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        ViewModelFactory factory = new ViewModelFactory(DataManager.getInstance());
        mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);

        userDetailsAdapter = new UserDetailsAdapter(this);
        mActivityMainBinding.recyclerView.setAdapter(userDetailsAdapter);
        mActivityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainViewModel.getUserDetailss().observe(this, new UserDetailsObserver());
        if(isNetworkConnected()){
            mMainViewModel.loadUserDetailssNetwork();
        }else{
            mActivityMainBinding.emptyView.setText(AppValidationMassages.No_INTERNET);
        }
    }


    @Override
    public void handleError(Throwable throwable) {
        //Handle Error here
    }

    @Override
    public void onUserDetailsClicked(UserDetails userDetails) {
       // Handle on item click from list
    }

    @Override
    public void openLoginScreen() {
        Intent intent = LoginActivity.newIntent(MainActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void localDatafetch() {
        mMainViewModel.getDataManager().getAppDbHelper().getAllUsers().observe(this,new UserDetailsObserver());
    }

    private class UserDetailsObserver implements Observer<List<UserDetails>> {

        @Override
        public void onChanged(@Nullable List<UserDetails> userDetails) {
            if (userDetails == null) return;
            userDetailsAdapter.setItems(userDetails);

            if (userDetails.isEmpty()) {
                mActivityMainBinding.emptyView.setVisibility(View.VISIBLE);
            } else {
                mActivityMainBinding.emptyView.setVisibility(View.GONE);
            }
        }
    }

}
