package com.niit.spiritairlinepoc.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.niit.spiritairlinepoc.data.DataManager;
import com.niit.spiritairlinepoc.data.network.model.UserDetails;
import com.niit.spiritairlinepoc.data.network.model.UserDetailsResponse;
import com.niit.spiritairlinepoc.ui.base.BaseViewModel;

import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel<MainScreenNavigator> {

    private MutableLiveData<List<UserDetails>> userDetailss;

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
        userDetailss = new MutableLiveData<>();
    }

    MutableLiveData<List<UserDetails>> getUserDetailss() {
        return userDetailss;
    }

    public void loadUserDetailssNetwork() {
        setIsLoading(true);
        getDataManager().getUserService().getApiService().getUserDetails().enqueue(new UserDetailsCallback());
    }


    public void loadUserDetailsLocal() {
       getNavigator().localDatafetch();
    }

   public void onLogout() {
       getDataManager().getSharedPreference().setAccessToken(null);
       getNavigator().openLoginScreen();
   }

    private void setUserDetailss(List<UserDetails> userDetailss) {
        setIsLoading(false);
        this.userDetailss.postValue(userDetailss);
    }

    /**
     * Callback
     **/
    private class UserDetailsCallback implements Callback<UserDetailsResponse> {

        @Override
        public void onResponse(@NonNull Call<UserDetailsResponse> call, @NonNull Response<UserDetailsResponse> response) {
            UserDetailsResponse userDetailsResponse = response.body();
            if (userDetailsResponse != null) {
              getDataManager().getAppDbHelper().saveUserDetails(userDetailsResponse.getUserDetails());
              setUserDetailss(userDetailsResponse.getUserDetails());
            } else {
                setUserDetailss(Collections.<UserDetails>emptyList());
            }
        }

        @Override
        public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
            setUserDetailss(Collections.<UserDetails>emptyList());
        }
    }
}
