package com.cundong.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class PatchApkUtils {

    // 成功
    private static final int WHAT_SUCCESS = 1;

    // 本地安装的微博MD5不正确
    private static final int WHAT_FAIL_OLD_MD5 = -1;

    // 新生成的微博MD5不正确
    private static final int WHAT_FAIL_GEN_MD5 = -2;

    // 合成失败
    private static final int WHAT_FAIL_PATCH = -3;

    // 获取源文件失败
    private static final int WHAT_FAIL_GET_SOURCE = -4;

    // 未知错误
    private static final int WHAT_FAIL_UNKNOWN = -5;

    public int patchApk(Context mContext,String oldRealMD5) {
//        if (packageInfo != null) {

//          请求当前旧版的真正 MD5值
//            requestOldMD5(packageInfo.versionCode, packageInfo.versionName);
            //获取 本地 最版本的文件路径
            String oldApkSource = ApkUtils.getSourceApkPath(mContext, Constants.TEST_PACKAGENAME);

            if (!TextUtils.isEmpty(oldApkSource)) {

                // 校验一下本地安装APK的MD5是不是和真实的MD5一致  mCurentRealMD5(服务器上新版本的MD5)
                if (true) {//SignUtils.checkMd5(oldApkSource, "mCurentRealMD5")
                    //合并新版本
                    int patchResult = PatchUtils.patch(oldApkSource, Constants.NEW_APK_PATH, Constants.PATCH_PATH);

                    if (patchResult == 0) {//合并结果
                        //检测 合并的apk 是否与服务器 apk md5一样 mNewRealMD5(服务器上新版本的MD5)
                        if (true) {//SignUtils.checkMd5(Constants.NEW_APK_PATH, "mNewRealMD5")
                            //增量升级成功
                            return WHAT_SUCCESS;
                        } else {
                            ////增量升级失败
                            return WHAT_FAIL_GEN_MD5;
                        }
                    } else {
                        return WHAT_FAIL_PATCH;
                    }
                } else {
                    return WHAT_FAIL_OLD_MD5;
                }
            } else {
                return WHAT_FAIL_GET_SOURCE;
            }
//        } else {
//            return WHAT_FAIL_UNKNOWN;
//        }
    }

    public String patchManager(Context mContext,String oldApkSource ,String newApkSource , String patchFilePath){

        int result =  patchFile( mContext, oldApkSource , newApkSource ,  patchFilePath);
        String text = null;
        switch (result) {
            case WHAT_SUCCESS: {
                text = "新apk已合成成功：" + newApkSource;
                ApkUtils.installApk(mContext, newApkSource);
                break;
            }
            case WHAT_FAIL_OLD_MD5: {
                text = "现在安装的WeiboV5.5的MD5不对！";
                break;
            }
            case WHAT_FAIL_GEN_MD5: {
                text = "合成完毕，但是合成得到的apk MD5不对！";
                break;
            }
            case WHAT_FAIL_PATCH: {
                text = "新apk已合成失败！";
                break;
            }
            case WHAT_FAIL_GET_SOURCE: {
                text = "无法获取微博客户端的源apk文件，只能整包更新了！";
                break;
            }
        }
        return text;
    }

    public int patchFile(Context mContext,String oldApkSource ,String newApkSource , String patchFilePath) {

        if (mContext != null) {
            //本地APK文件存在
            if (!TextUtils.isEmpty(oldApkSource)) {
                // 校验一下本地安装APK的MD5是不是和真实的MD5一致  mCurentRealMD5(服务器上新版本的MD5)
                if (true) {//SignUtils.checkMd5(oldApkSource, "mCurentRealMD5")
                    //合并新版本
                    int patchResult = PatchUtils.patch(oldApkSource, newApkSource, patchFilePath);

                    if (patchResult == 0) {//合并结果
                        //检测 合并的apk 是否与服务器 apk md5一样 mNewRealMD5(服务器上新版本的MD5)
                        if (true) {//SignUtils.checkMd5(Constants.NEW_APK_PATH, "mNewRealMD5")
                            //增量升级成功
                            return WHAT_SUCCESS;
                        } else {
                            ////增量升级失败
                            return WHAT_FAIL_GEN_MD5;
                        }
                    } else {
                        return WHAT_FAIL_PATCH;
                    }
                } else {
                    return WHAT_FAIL_OLD_MD5;
                }
            } else {
                return WHAT_FAIL_GET_SOURCE;
            }
        } else {
            return WHAT_FAIL_UNKNOWN;
        }
    }
}
