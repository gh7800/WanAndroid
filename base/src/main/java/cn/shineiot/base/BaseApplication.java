package cn.shineiot.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import org.greenrobot.greendao.query.QueryBuilder;

import cn.shineiot.base.utils.DBHelper;

/**
 * @author GF63
 */
public abstract class BaseApplication extends Application {
    public static String DateBaseName = "wanAndroid.bd";

    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

	    //写在Aroute init 之前
	    if (isDebug()) {
		    ARouter.openDebug();
		    ARouter.openLog();
	    }
	    ARouter.init(this);

        //创建数据库，上线log改为false
        DBHelper.initDataBase(context,DateBaseName);
        if(!isDebug()) {
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }else{
            QueryBuilder.LOG_SQL = false;
            QueryBuilder.LOG_VALUES = false;
        }

    }

    /**
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * @param application
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);

    public static Context context(){
        return context;
    }

    public boolean isDebug() {
        try {
            ApplicationInfo info = getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}
