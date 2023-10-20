package com.example.quantumdynamics.ui.login;

public interface LoginNavigation {

    void handleError(Throwable throwable);

    void login();

    void openDashboardActivity();
}