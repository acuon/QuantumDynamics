package com.example.quantumdynamics.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.quantumdynamics.R;
import com.example.quantumdynamics.databinding.ActivityDashboardBinding;
import com.example.quantumdynamics.base.BaseActivity;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onViewClicked(View view) {

    }

    public static void present(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}

