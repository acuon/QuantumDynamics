package com.example.quantumdynamics.di;


import com.example.quantumdynamics.ui.dashboard.viewmodel.DashboardViewModel;
import com.example.quantumdynamics.ui.login.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public final class AppModule {

    @Provides
    @Singleton
    public LoginViewModel provideHomeViewModel() {
        return new LoginViewModel();
    }

    @Provides
    @Singleton
    public DashboardViewModel provideDashboardViewModel() {
        return new DashboardViewModel();
    }

}

