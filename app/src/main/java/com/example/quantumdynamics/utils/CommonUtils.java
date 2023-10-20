package com.example.quantumdynamics.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Patterns;

import androidx.appcompat.app.AlertDialog;

import com.example.quantumdynamics.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CommonUtils {

    private CommonUtils() {

    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String getTotalMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
        }
        return formatSize(memoryInfo.totalMem);
    }

    public static String getAvailableStorageCapacity() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        return formatSize(bytesAvailable);
    }

    public static String getTotalStorageCapacity() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long totalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
        return formatSize(totalBytes);
    }


    private static String formatSize(long size) {
        String suffix = "B";
        double formattedSize = size;
        if (size >= 1024) {
            suffix = "KB";
            formattedSize = size / 1024.0;
            if (formattedSize >= 1024) {
                suffix = "MB";
                formattedSize /= 1024.0;
                if (formattedSize >= 1024) {
                    suffix = "GB";
                    formattedSize /= 1024.0;
                }
            }
        }
        return String.format("%.2f %s", formattedSize, suffix);
    }

    public static void showExitDialog(Context context, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.exit))
                .setMessage(context.getString(R.string.exit_warning_message))
                .setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
