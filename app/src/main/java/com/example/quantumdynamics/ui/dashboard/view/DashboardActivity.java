package com.example.quantumdynamics.ui.dashboard.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.core.content.FileProvider;

import com.example.quantumdynamics.BR;
import com.example.quantumdynamics.R;
import com.example.quantumdynamics.base.BaseActivity;
import com.example.quantumdynamics.databinding.ActivityDashboardBinding;
import com.example.quantumdynamics.ui.dashboard.DashboardNavigation;
import com.example.quantumdynamics.ui.dashboard.model.SystemSpecifications;
import com.example.quantumdynamics.ui.dashboard.viewmodel.DashboardViewModel;
import com.example.quantumdynamics.utils.AppConstants;
import com.example.quantumdynamics.utils.CommonUtils;

import java.io.File;
import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> implements DashboardNavigation {

    private final String TAG = "DASHBOARD_ACTIVITY";
    private ActivityDashboardBinding binding;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri photoUri;

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
    }

    @Override
    protected void onViewClicked(View view) {

    }

    public static void present(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private File createImageFile() throws IOException {
        String timeStamp = CommonUtils.getTimestamp();
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            return File.createTempFile(imageFileName, AppConstants.FILE_FORMAT_JPG, storageDir);
        } else {
            throw new IOException(getString(R.string.storage_directory_full));
        }
    }

    private void openCameraAndTakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException exception) {
                handleError(exception);
                Log.e(TAG, getString(R.string.an_error_occurred_while_capture), exception);
            }
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, AppConstants.FILE_PROVIDER, photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (photoUri != null) {
                viewModel.setImageUri(photoUri);
            }
        }
    }

    private void requestCameraPermission() {
        if (!hasPermission(Manifest.permission.CAMERA)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            openCameraAndTakePicture();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCameraAndTakePicture();
            } else {
                showToast(getString(R.string.necessary_permissions_required));
            }
        }
    }

    private SystemSpecifications createSystemSpecsModel() {
        String processorDetails = System.getProperty(AppConstants.OS_ARCH);
        String totalMemory = CommonUtils.getTotalMemory(this);
        String storageCapacity = CommonUtils.getTotalStorageCapacity();
        SystemSpecifications specs = new SystemSpecifications(Build.MODEL, Build.VERSION.RELEASE, processorDetails, totalMemory, storageCapacity);
        Log.d(TAG, "Specs" + specs.getDeviceModel() + " " + specs.getStorageCapacity() + " " + specs.getAndroidVersion() + " " + specs.getProcessorDetails() + " " + specs.getRamDetails());
        return specs;
    }

    @Override
    public void handleError(Throwable throwable) {
        showToast(getString(R.string.an_error_occurred_please_try_again));
        Log.e(TAG, getString(R.string.an_error_occurred), throwable);
    }

    @Override
    public void showSystemSpecs() {
        viewModel.setSystemSpecifications(createSystemSpecsModel());
        Log.d(TAG, "show system specs");
    }

    @Override
    public void exitApp() {
        Log.d(TAG, "exit app");
        CommonUtils.showExitDialog(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) finish();
            }
        });
    }

    @Override
    public void openCamera() {
        requestCameraPermission();
        Log.d(TAG, "open camera");
    }
}

