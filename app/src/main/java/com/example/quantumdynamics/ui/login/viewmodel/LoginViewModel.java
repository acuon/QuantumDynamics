package com.example.quantumdynamics.ui.login.viewmodel;

import android.text.TextUtils;

import com.example.quantumdynamics.base.BaseViewModel;
import com.example.quantumdynamics.ui.login.LoginNavigation;
import com.example.quantumdynamics.ui.login.model.User;
import com.example.quantumdynamics.utils.CommonUtils;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends BaseViewModel<LoginNavigation> {

    @Inject
    public LoginViewModel() {
        super();
    }

    public boolean isEmailAndPasswordValid(@Nullable String email, @Nullable String password) {
        if (email == null) return false;
        else if (TextUtils.isEmpty(email)) return false;
        else if (!CommonUtils.isValidEmail(email)) return false;
        else if (password == null) return false;
        else if (TextUtils.isEmpty(password)) return false;
        else return true;
    }

    public void onLoginClick() {
        getNavigator().login();
    }

    public void login(User user) {
        setIsLoading(true);
        try {
            setIsLoading(false);
            getNavigator().openDashboardActivity();
        } catch (Exception e) {
            setIsLoading(false);
            getNavigator().handleError(e);
        }
    }
}
