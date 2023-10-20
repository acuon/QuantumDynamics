package com.example.quantumdynamics.ui.login;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.quantumdynamics.R;
import com.example.quantumdynamics.databinding.ActivityLoginBinding;
import com.example.quantumdynamics.base.BaseActivity;
import com.example.quantumdynamics.ui.dashboard.DashboardActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        bindViewEvents();
        setup();
    }

    private void bindViewEvents() {
        binding.login.setOnClickListener(getClickListener());
    }

    private void setup() {
    }

    @Override
    protected void onViewClicked(View view) {
        if(view == binding.login) {
            Toast.makeText(LoginActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            DashboardActivity.present(this);
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}