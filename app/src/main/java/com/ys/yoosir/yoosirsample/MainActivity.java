package com.ys.yoosir.yoosirsample;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.cundong.utils.DiffUtils;
import com.cundong.utils.PatchUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private final int SELECT_IMAGE = 101;
    private final int CROP_IMAGE = 102;
    private ImageView showView;
    private Uri selectUri;

    private String rootPath;
    private Uri saveUri;

    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.holo_blue_light, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_red_light);
        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshLayout.setRefreshing(true);
                Toast.makeText(MainActivity.this, "下拉ing", Toast.LENGTH_SHORT).show();
//				initData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下拉完成", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                }, 6000);

                String oldApk = null;
                try {
                    ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
                    oldApk =  appInfo.sourceDir;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
//                String newApkPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"app-old2new.apk";
//                String patchFile = Environment.getExternalStorageDirectory().getPath()+File.separator+"app-debug.patch";
//                if( new File(patchFile).exists()) {
//                    PatchUtils.patch(oldApk, newApkPath, patchFile);
//                }
            }
        });

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        float desity = getResources().getDisplayMetrics().density;
        int desityDpi = getResources().getDisplayMetrics().densityDpi;

        Log.i("test", "width= "+width+", height="+height+",desity="+desity+",desityDpi="+desityDpi);

        float a1=getResources().getDimension(R.dimen.activity_vertical_margin1);
        int a2=getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin1);
        int a3=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin1);

        float b1=getResources().getDimension(R.dimen.activity_vertical_margin2);
        int b2=getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin2);
        int b3=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin2);

        float c1=getResources().getDimension(R.dimen.activity_vertical_margin3);
        int c2=getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin3);
        int c3=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin3);

        Log.i("test", "getDimension= "+a1+", getDimensionPixelOffset="+a2+",getDimensionPixelSize="+a3);
        Log.i("test", "getDimension= "+b1+", getDimensionPixelOffset="+b2+",getDimensionPixelSize="+b3);
        Log.i("test", "getDimension= "+c1+", getDimensionPixelOffset="+c2+",getDimensionPixelSize="+c3);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                refreshLayout.setRefreshing(false);
                showView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        showView = (ImageView) findViewById(R.id.showImage);
        showView.setImageResource(R.mipmap.ic_launcher);

        rootPath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"ystemp.jpeg";
        File file = new File(rootPath);
        saveUri = Uri.fromFile(file);

//        String oldApkPath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"app-debug1.apk";
//        String newApkPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"app-debug2.apk";
//        String patchFile = Environment.getExternalStorageDirectory().getPath()+File.separator+"app-debug.patch";
//        int result = DiffUtils.genDiff(oldApkPath,newApkPath,patchFile);
//        Log.i("diff","result=="+result);

//        String oldApk = null;
//        try {
//            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
//            oldApk =  appInfo.sourceDir;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        String oldApkPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"cyc_android-ysm-debug1.apk";
        String newApkPath = Environment.getExternalStorageDirectory().getPath()+File.separator+"cyc_android-ysm-debug-old2new.apk";
        String patchFile = Environment.getExternalStorageDirectory().getPath()+File.separator+"cyc_android-ysm-debug.patch";
//        if( new File(patchFile).exists()) {
            PatchUtils.patch(oldApkPath, newApkPath, patchFile);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectImage(View v){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_IMAGE);
//        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT){
//            startActivityForResult(intent, SELECT_PIC_KITKAT);
//        }else{
//            startActivityForResult(intent, SELECT_PIC);
//        }
    }

    public void cropImage(View  v){
//        UCrop.of(sourceUri, destinationUri)
//                .withAspectRatio(16, 9)
//                .withMaxResultSize(maxWidth, maxHeight)
//                .start(context);
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(selectUri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 400);
//
        intent.putExtra("outputY", 400);

        intent.putExtra("scale", true);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveUri);

        intent.putExtra("return-data", false);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("noFaceDetection", true); // no face detection

        startActivityForResult(intent, CROP_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SELECT_IMAGE:
                selectUri = data.getData();
                if(selectUri != null) {
                    Log.i(TAG, "select -- uri=" + selectUri.toString());
                    showView.setImageURI(selectUri);
                }
                break;
            case CROP_IMAGE:
                if(saveUri != null){
                    Log.i(TAG,"crop -- save uri="+saveUri.toString());
                    Log.i(TAG, "crop -- save path=" + saveUri.getPath());
                    showView.setImageURI(saveUri);
                }
                break;
        }
    }
}
