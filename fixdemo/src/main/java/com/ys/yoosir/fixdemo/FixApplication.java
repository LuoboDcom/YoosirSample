package com.ys.yoosir.fixdemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.dodola.rocoofix.RocooFix;

import java.io.File;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class FixApplication extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //打补丁
        RocooFix.init(this);
        RocooFix.initPathFromAssets(this,"patch.jar");
//        String patchPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"fix_patch.jar";
//        if(new File(patchPath).exists()){
//            RocooFix.applyPatch(this,patchPath);
//        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
