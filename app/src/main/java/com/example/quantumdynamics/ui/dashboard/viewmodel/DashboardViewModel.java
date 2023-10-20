package com.example.quantumdynamics.ui.dashboard.viewmodel;

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
    private SystemSpecifications demo = new SystemSpecifications("model", "version", "processor", 0L, "storage");
    private final ObservableField<SystemSpecifications> systemSpecs = new ObservableField<>();

    public ObservableBoolean getIsFocusOnButtonContainer() {
        return isButtonContainer;
    }
    public ObservableBoolean getCameraButtonClicked() {
        return cameraButtonClicked;
    }

    public SystemSpecifications getSystemSpecs() {
        return systemSpecs.get();
    }

    public void setSystemSpecifications(SystemSpecifications specs) {
        systemSpecs.set(specs);
    }

    public void setCameraButtonClicked(Boolean value) {
        cameraButtonClicked.set(value);
    }

    private void setIsButtonContainerValue(boolean value) {
        isButtonContainer.set(value);
    }

    public void showSystemSpecs() {
        setIsButtonContainerValue(false);
        setCameraButtonClicked(false);
        getNavigator().showSystemSpecs();
    }

    public void onCameraClicked() {
        setIsButtonContainerValue(false);
        setCameraButtonClicked(true);
        getNavigator().openCamera();
    }

    public void onExitClick() {
        setIsButtonContainerValue(false);
    }

    public void onCloseButtonClicked() {
        setIsButtonContainerValue(true);
    }
}
