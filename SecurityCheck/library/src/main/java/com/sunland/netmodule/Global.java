package com.sunland.netmodule;

import android.os.Build;
import android.os.Environment;

/**
 * Created by LSJ on 2017/10/17.
 */

public class Global {
    public final static String appName = "hzydjw";
    public final static String sdPath = Environment.getExternalStorageDirectory().getPath();
    public final static String ip = "192.168.1.180";
    public static String port = "8080";
    public final static String postfix = "/hzydjw/services/ITxlQueryDepListService";

    //本机信息
    public static String imei = "";
    public static String imsi1 = " ";
    public static String imsi2 = "";
    public static String gpsX = "";//经度
    public static String gpsY = "";//纬度
    public static String gpsinfo = gpsX + gpsY;

    public static String brand = Build.BRAND;
    public static String model = Build.MODEL + " " + Build.VERSION.SDK_INT;

}
