package com.niit.spiritairlinepoc.ui.base;


import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.niit.spiritairlinepoc.utils.NetworkUtils;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
//    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }


    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());

        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;

        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


}

