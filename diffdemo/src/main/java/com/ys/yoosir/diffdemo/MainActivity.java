package com.ys.yoosir.diffdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cundong.utils.DiffUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String oldApkPath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"cyc_android-ysm-debug1.apk";
        String newApkPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"cyc_android-ysm-debug2.apk";
        String patchFile = Environment.getExternalStorageDirectory().getPath()+File.separator+"cyc_android-ysm-debug.patch";
        int result = DiffUtils.genDiff(oldApkPath,newApkPath,patchFile);
        Log.i("diff","result=="+result);
    }
}
