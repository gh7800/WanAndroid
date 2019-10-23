package cn.shineiot.base.utils;

import android.content.Context;

import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.config.MDialogConfig;

public class LoadingDialog {

    public static void showDialog(Context context){
        if(!MProgressDialog.isShowing()) {
            MProgressDialog.showProgress(context);
        }
    }
    public static void showDialog(Context context,String msg){
        if(!MProgressDialog.isShowing()) {
            MProgressDialog.showProgress(context, msg);
        }
    }
    public static void showDialog(Context context,String msg,MDialogConfig mDialogConfig){
        if(!MProgressDialog.isShowing()) {
            MProgressDialog.showProgress(context, msg, mDialogConfig);
        }
    }
    public static void hideDialog(){
        if(isShow()){
            MProgressDialog.dismissProgress();
        }
    }
    public static boolean isShow(){
        return MProgressDialog.isShowing();
    }
}
