package cn.shineiot.login;

import android.app.Application;

import cn.shineiot.base.BaseApplication;

/**
 * @author GF63
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {

    }
}
