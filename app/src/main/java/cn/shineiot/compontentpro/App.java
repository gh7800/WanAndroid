package cn.shineiot.compontentpro;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.shineiot.base.AppConfig;
import cn.shineiot.base.BaseApplication;


/**
 * @author GF63
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //写在Aroute init 之前
        if (isDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

        initModuleApp(this);
        initModuleData(this);
    }

    public boolean isDebug() {
        try {
            ApplicationInfo info = getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
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
