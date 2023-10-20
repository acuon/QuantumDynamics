package com.example.quantumdynamics.ui.dashboard.view;

import android.Manifest;
import android.content.Context;
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
import com.bumptech.glide.Glide;
import com.example.quantumdynamics.BR;
import com.example.quantumdynamics.R;
import com.example.quantumdynamics.base.BaseActivity;
import com.example.quantumdynamics.databinding.ActivityDashboardBinding;
import com.example.quantumdynamics.ui.dashboard.DashboardNavigation;
import com.example.quantumdynamics.ui.dashboard.model.SystemSpecifications;
import com.example.quantumdynamics.ui.dashboard.viewmodel.DashboardViewModel;
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
            return File.createTempFile(imageFileName, ".jpg", storageDir);
        } else {
            throw new IOException("Storage directory is null");
        }
    }

    private void openCameraAndTakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException exception) {
                Log.e(TAG, "An error occurred while capturing image", exception);
            }
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this,
                        "com.example.quantumdynamics.fileprovider",
                        photoFile);
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
//                Glide.with(this).load(photoUri).into(binding.image);
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
                //TODO("Handle the case where permission is denied")
            }
        }
    }

    private SystemSpecifications createSystemSpecsModel() {
        String processorDetails = System.getProperty("os.arch");
        String totalMemory = CommonUtils.getTotalMemory(this);
        String storageCapacity = CommonUtils.getTotalStorageCapacity();
        SystemSpecifications specs = new SystemSpecifications(Build.MODEL, Build.VERSION.RELEASE, processorDetails, totalMemory, storageCapacity);
        Log.d(TAG, "Specs" + specs.getDeviceModel() + " " + specs.getStorageCapacity() + " " + specs.getAndroidVersion() + " " + specs.getProcessorDetails() + " " + specs.getRamDetails());
        return specs;
    }
    @Override
    public void handleError(Throwable throwable) {
        showToast("An Error Occurred, Please Try Again");
        Log.e(TAG, "An error occurred", throwable);
    }

    @Override
    public void showSystemSpecs() {
        viewModel.setSystemSpecifications(createSystemSpecsModel());
//        showToast("show system specs");
        Log.d(TAG, "show system specs");
    }

    @Override
    public void exitApp() {
//        showToast("exit app");
        Log.d(TAG, "exit app");
        finish();
    }

    @Override
    public void openCamera() {
        requestCameraPermission();
        showToast("open camera");
        Log.d(TAG, "open camera");
    }

    @Override
    public void closeContainer() {
        showToast("close container");
        Log.d(TAG, "close container");
    }
}

