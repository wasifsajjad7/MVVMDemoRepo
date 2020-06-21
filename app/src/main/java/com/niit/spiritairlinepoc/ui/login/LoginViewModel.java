package com.niit.spiritairlinepoc.ui.login;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Patterns;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.niit.spiritairlinepoc.MvvmApp;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.data.network.model.LoginResponse;
import com.niit.spiritairlinepoc.ui.base.BaseViewModel;
import com.niit.spiritairlinepoc.utils.AppConstants;
import com.niit.spiritairlinepoc.utils.AppValidationMassages;
import com.niit.spiritairlinepoc.utils.CommonUtils;
import com.niit.spiritairlinepoc.utils.NetworkUtils;

import org.json.JSONObject;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public MutableLiveData<String> token = new MutableLiveData<>();


    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }


    public void onClick() {
        getNavigator().login();
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password


        if (TextUtils.isEmpty(email)) {
            getNavigator().handleMassages(AppValidationMassages.EMPTY_EMAIL);
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getNavigator().handleMassages(AppValidationMassages.INVALID_EMAIL);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            getNavigator().handleMassages(AppValidationMassages.EMPTY_PASSWORD);
            return false;
        }
        getNavigator().handleMassages("");
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(String email, String password) {
            setIsLoading(true);
            Map<String, Object> jsonParams = new ArrayMap<>();
            jsonParams.put(AppConstants.EMAIL,email);
            jsonParams.put(AppConstants.PASSWORD,  password);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
            getDataManager().getUserService().getApiService().login(body).enqueue(new LoginViewModel.LoginCallback());
    }

    private void setLoginDetails(String token) {
        setIsLoading(false);
        getDataManager().getSharedPreference().setAccessToken(token);
        getNavigator().openMainActivity();
    }

    /**
     * Callback
     **/
    private class LoginCallback implements Callback<LoginResponse> {

        @Override
        public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
            LoginResponse loginResponse = response.body();

            if (loginResponse != null) {
                setLoginDetails(loginResponse.getToken());
            } else {
                setIsLoading(false);
                getNavigator().handleMassages(AppValidationMassages.DATA_BLANK);
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                setIsLoading(false);
                getNavigator().handleError(throwable);
        }
    }

}
