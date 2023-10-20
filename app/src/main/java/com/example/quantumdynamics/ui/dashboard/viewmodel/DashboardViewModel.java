package com.example.quantumdynamics.ui.dashboard.viewmodel;

import android.net.Uri;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.quantumdynamics.base.BaseViewModel;
import com.example.quantumdynamics.ui.dashboard.DashboardNavigation;
import com.example.quantumdynamics.ui.dashboard.model.SystemSpecifications;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardViewModel extends BaseViewModel<DashboardNavigation> {

    @Inject
    public DashboardViewModel() {
        super();
    }

    private final ObservableBoolean isButtonContainer = new ObservableBoolean(true);
    private final ObservableBoolean cameraButtonClicked = new ObservableBoolean();
    private final ObservableField<SystemSpecifications> systemSpecs = new ObservableField<>();
    private final ObservableField<Uri> imageUri = new ObservableField<>();

    public ObservableBoolean getIsButtonContainer() {
        return isButtonContainer;
    }

    public ObservableBoolean getCameraButtonClicked() {
        return cameraButtonClicked;
    }

    public ObservableField<SystemSpecifications> getSystemSpecs() {
        return systemSpecs;
    }

    public ObservableField<Uri> getImageUri() {
        return imageUri;
    }

    public void setSystemSpecifications(SystemSpecifications specs) {
        systemSpecs.set(specs);
    }

    public void setImageUri(Uri uri) {
        setIsButtonContainerValue(false);
        setCameraButtonClicked(true);
        imageUri.set(uri);
    }

    public void showSystemSpecs() {
        getNavigator().showSystemSpecs();
        setIsButtonContainerValue(false);
        setCameraButtonClicked(false);
    }

    public void onCameraClicked() {
        getNavigator().openCamera();
    }

    public void onExitClick() {
        getNavigator().exitApp();
    }

    public void onCloseButtonClicked() {
        setIsButtonContainerValue(true);
    }

    private void setIsButtonContainerValue(boolean value) {
        isButtonContainer.set(value);
    }

    private void setCameraButtonClicked(boolean value) {
        cameraButtonClicked.set(value);
    }
}
