package com.example.quantumdynamics.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<B extends ViewBinding> extends AppCompatActivity {

    B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preInit();
        binding = getViewBinding();
        setContentView(binding.getRoot());
        init();
        setupView();
        bindViewEvents();
        bindViewModel();
    }

    private void init() {
    }

    protected void preInit() {

    }

    protected abstract void setupView();

    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    protected void bindViewEvents() {
        if(binding != null) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard();
                }
            });
        }
    }

    protected abstract void bindViewModel();

    protected abstract B getViewBinding();

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideSoftKeyboard();
            onClick(view);
        }
    };

    protected abstract void onClick(View view);

}
