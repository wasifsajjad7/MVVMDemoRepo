package com.niit.spiritairlinepoc.utils;

import android.util.Patterns;


public final class CommonUtils {

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
