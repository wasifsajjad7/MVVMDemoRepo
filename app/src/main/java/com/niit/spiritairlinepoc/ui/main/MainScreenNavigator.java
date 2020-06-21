package com.niit.spiritairlinepoc.ui.main;


public interface MainScreenNavigator {

    void handleError(Throwable throwable);

    void openLoginScreen();

    void localDatafetch();

}
