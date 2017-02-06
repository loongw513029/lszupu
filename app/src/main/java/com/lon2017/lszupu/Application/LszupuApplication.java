package com.lon2017.lszupu.Application;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


import java.io.File;

//import Utils.Constant;
//import Utils.SPUtils;
//import Utils.VolleyUtil;
//import View.CustomDialog;
//import cn.jpush.android.api.JPushInterface;
//import mabeijianxi.camera.VCamera;
//import mabeijianxi.camera.util.DeviceUtils;

/**
 * For developer startup JPush SDK
 * <p/>
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class LszupuApplication extends MultiDexApplication {
    private static final String TAG = "JPush";


    @Override
    public void onCreate() {
        Log.d(TAG, "[LszupuApplication] onCreate");
        super.onCreate();
//        LeakCanary.install(this);// 初始化 JPush
        CrashHandler crashHandler = CrashHandler.getInstance();
//         注册crashHandler
        crashHandler.init(getApplicationContext());
        // 发送以前没发送的报告(可选)
        // crashHandler.sendPreviousReportsToServer();
        initSmallVideo(this);
    }

    public void initSmallVideo(Context context) {
       /* // 设置拍摄视频缓存路径
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
            } else {
                VCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
                        "/sdcard-ext/")
                        + "/mabeijianxi/");
            }
        } else {
            VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
        }*/
        //VCamera.setVideoCachePath(getApplicationContext().getExternalCacheDir() + "/movies/");
        //VCamera.setDebugMode(true);
        //VCamera.initialize(context);
    }
}
