package com.niit.spiritairlinepoc.ui.login;

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void login();

    void handleMassages(String massage);

    void openMainActivity();
}
