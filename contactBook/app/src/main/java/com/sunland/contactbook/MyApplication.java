package com.sunland.contactbook;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.sunland.netmodule.Global;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    private void getPhoneInfo() {
        TelephonyManager tpm = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            Global.imei = tpm.getDeviceId();
            Global.imsi1 = tpm.getSubscriberId();
        }
    }
}
