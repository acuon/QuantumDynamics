package com.example.quantumdynamics.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.quantumdynamics.R;
import com.example.quantumdynamics.databinding.ActivityLoginBinding;
import com.example.quantumdynamics.ui.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);


    @Override
    protected void setupView() {

    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onClick(View view) {

    }
}