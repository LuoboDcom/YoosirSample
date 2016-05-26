package com.javatest;

import com.cundong.utils.SignUtils;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class Test {

    public static void main(String[] args){
        System.out.println("start=="+System.currentTimeMillis());
        SignUtils.checkMd5("E:\\android_studio_workspace\\DIFF_PATCH\\2016-5-26\\cyc_android-ysm-debug2.apk","");
        System.out.println("end=="+System.currentTimeMillis());

        System.out.println("start=="+System.currentTimeMillis());
        SignUtils.checkMd5("E:\\android_studio_workspace\\DIFF_PATCH\\2016-5-26\\cyc_android-ysm-debug-old2new.apk","");
        System.out.println("end=="+System.currentTimeMillis());
        /**
         *
         * 2016-5-25\a.txt
         * start==1464140485457
         md5===0cc175b9c0f1b6a831c399e269772661
         end==1464140485466
         */

        /**  a.txt
         * start==1464140520802
         md5===92eb5ffee6ae2fec3ad71c777531578f
         end==1464140520816
         */

        /**
        start==1464216234622
        md5===534264aa2a9d5b7aa8532e37b938d6a1
        end==1464216234710
        start==1464216234711
        md5===534264aa2a9d5b7aa8532e37b938d6a1
        end==1464216234740
         **/
    }
}
