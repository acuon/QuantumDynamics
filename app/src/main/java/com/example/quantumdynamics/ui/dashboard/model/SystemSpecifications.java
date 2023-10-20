package com.example.quantumdynamics.ui.dashboard.model;

public class SystemSpecifications {

    private String deviceModel;
    private String androidVersion;
    private String processorDetails;
    private Long ramDetails;
    private String storageCapacity;

    public SystemSpecifications(String deviceModel, String androidVersion, String processorDetails, Long ramDetails, String storageCapacity) {
        this.deviceModel = deviceModel;
        this.androidVersion = androidVersion;
        this.processorDetails = processorDetails;
        this.ramDetails = ramDetails;
        this.storageCapacity = storageCapacity;
    }

    public String getDeviceModel() {
        return "Device Model: " + deviceModel;
    }

    public String getAndroidVersion() {
        return "Android Version: " + androidVersion;
    }

    public String getProcessorDetails() {
        return "Processor: " + processorDetails;
    }

    public String getRamDetails() {
        return "RAM:" + ramDetails;
    }

    public String getStorageCapacity() {
        return "Storage: " + storageCapacity;
    }
}
