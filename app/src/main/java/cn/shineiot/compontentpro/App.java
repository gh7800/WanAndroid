package cn.shineiot.compontentpro;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import cn.shineiot.base.AppConfig;
import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.utils.SPUtils;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;


/**
 * @author GF63
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //AndroidAutoSize屏幕适配初始化
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                //LogUtil.e("onAdaptBefore--"+target.getClass().getName());
            }

            @Override
            public void onAdaptAfter(Object target, Activity activity) {
                //LogUtil.e("onAdaptAfter--"+target.getClass().getName());
            }
        });
        boolean isNight = SPUtils.getInstance().getBoolean("night");
        if(isNight){
            setNightMode(isNight);
        }

        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        for (String moduleApp : AppConfig.moduleName) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApp = (BaseApplication) clazz.newInstance();
                baseApp.initModuleApp(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initModuleData(Application application) {
        for (String moduleApp : AppConfig.moduleName) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApp = (BaseApplication) clazz.newInstance();
                baseApp.initModuleData(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 夜间模式
     */
    public static void setNightMode(boolean isNight){
        if(isNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        SPUtils.getInstance().put("night", isNight);
    }
}
