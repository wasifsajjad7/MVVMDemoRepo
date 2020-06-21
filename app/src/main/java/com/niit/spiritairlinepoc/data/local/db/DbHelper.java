package com.niit.spiritairlinepoc.data.local.db;

import androidx.lifecycle.LiveData;
import com.niit.spiritairlinepoc.data.network.model.UserDetails;
import java.util.List;


public interface DbHelper {

    LiveData<List<UserDetails>> getAllUsers();

     void saveUserDetails(List<UserDetails> userDetailsList);

}
