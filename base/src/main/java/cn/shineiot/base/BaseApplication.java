package cn.shineiot.base;

import android.app.Application;
import android.content.Context;

/**
 * @author GF63
 */
public abstract class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);

    public static Context context(){
        return context;
    }
}
