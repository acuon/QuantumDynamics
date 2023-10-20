package com.example.quantumdynamics.ui.dashboard.model;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

public class SystemSpecifications {

    private String deviceModel;
    private String androidVersion;
    private String processorDetails;
    private String ramDetails;
    private String storageCapacity;

    public SystemSpecifications(String deviceModel, String androidVersion, String processorDetails, String ramDetails, String storageCapacity) {
        this.deviceModel = deviceModel;
        this.androidVersion = androidVersion;
        this.processorDetails = processorDetails;
        this.ramDetails = ramDetails;
        this.storageCapacity = storageCapacity;
    }

    public SpannableString getDeviceModel() {
        return getFormattedString("DEVICE MODEL \n", deviceModel);
    }

    public SpannableString getAndroidVersion() {
        return getFormattedString("ANDROID VERSION \n", androidVersion);
    }

    public SpannableString getProcessorDetails() {
        return getFormattedString("PROCESSOR \n", processorDetails);
    }

    public SpannableString getRamDetails() {
        return getFormattedString("RAM \n", ramDetails);
    }

    public SpannableString getStorageCapacity() {
        return getFormattedString("STORAGE \n", storageCapacity);
    }

    private SpannableString getFormattedString(String prefix, String value) {
        SpannableString spannableString = new SpannableString(prefix + value);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, prefix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
