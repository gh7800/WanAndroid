package cn.shineiot.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.zhy.changeskin.SkinManager;

import cn.shineiot.base.utils.Constants;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.SPUtils;

/**
 * @author GF63
 */
public abstract class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SkinManager.getInstance().init(context);
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
