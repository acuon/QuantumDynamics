package com.example.quantumdynamics.ui.login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quantumdynamics.R;
import com.example.quantumdynamics.BR;
import com.example.quantumdynamics.databinding.ActivityLoginBinding;
import com.example.quantumdynamics.base.BaseActivity;
import com.example.quantumdynamics.ui.dashboard.view.DashboardActivity;
import com.example.quantumdynamics.ui.login.LoginNavigation;
import com.example.quantumdynamics.ui.login.viewmodel.LoginViewModel;
import com.example.quantumdynamics.ui.login.model.User;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigation {

    private final String TAG = "LOGIN_ACTIVITY";

    private ActivityLoginBinding binding;
//    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
//        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        bindViewEvents();
        setup();
    }

    private void bindViewEvents() {
//        binding.login.setOnClickListener(getClickListener());
    }

    private void setup() {
        DashboardActivity.present(this);
        finish();
    }

    @Override
    protected void onViewClicked(View view) {
//        if (view == binding.login) {
//            Toast.makeText(LoginActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
//            DashboardActivity.present(this);
//            finish();
//        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.e(TAG, "An Error Occurred", throwable);
        showToast("An Error Occurred, Please try again");
    }

    @Override
    public void login() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (viewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard();
            viewModel.login(new User(email, password));
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openDashboardActivity() {
        DashboardActivity.present(this);
        finish();
    }
}