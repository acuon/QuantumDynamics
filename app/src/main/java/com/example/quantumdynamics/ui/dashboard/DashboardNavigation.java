package com.example.quantumdynamics.ui.dashboard;

public interface DashboardNavigation {

    void handleError(Throwable throwable);

    void showSystemSpecs();

    void exitApp();

    void openCamera();

    void closeContainer();

}
