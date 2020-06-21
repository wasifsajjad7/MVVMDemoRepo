package com.niit.spiritairlinepoc.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import com.niit.spiritairlinepoc.BR;
import com.niit.spiritairlinepoc.R;
import com.niit.spiritairlinepoc.ViewModelFactory;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.databinding.ActivityLoginBinding;
import com.niit.spiritairlinepoc.ui.base.BaseActivity;
import com.niit.spiritairlinepoc.ui.main.MainActivity;
import com.niit.spiritairlinepoc.utils.AppValidationMassages;
import com.niit.spiritairlinepoc.utils.NetworkUtils;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>  implements LoginNavigator{

    private ActivityLoginBinding mActivityLoginBinding;
    private LoginViewModel mLoginViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.LoginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        ViewModelFactory factory = new ViewModelFactory(DataManager.getInstance());
        mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void handleMassages(String massage) {
        mActivityLoginBinding.txtErrorMassage.setText(massage);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void login() {
        String email = mActivityLoginBinding.txtEmailAddress.getText().toString();
        String password = mActivityLoginBinding.txtPassword.getText().toString();
        if (isNetworkConnected()) {
            if (mLoginViewModel.isEmailAndPasswordValid(email, password)) {
                hideKeyboard();
                mLoginViewModel.login(email, password);
            }
        }else{
            mActivityLoginBinding.txtErrorMassage.setText(AppValidationMassages.No_INTERNET);
        }

    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

}
