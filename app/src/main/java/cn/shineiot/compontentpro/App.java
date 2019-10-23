package cn.shineiot.compontentpro;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.shineiot.base.AppConfig;
import cn.shineiot.base.BaseApplication;
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

        //写在Aroute init 之前
        if (isDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

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
}
