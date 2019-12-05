package cn.shineiot.base.utils;

import android.content.Context;
import cn.shineiot.base.BaseApplication;
import cn.shineiot.base.greedao.DaoMaster;
import cn.shineiot.base.greedao.DaoSession;


/**
 * @author wangs
 */
public class DBHelper {

	private static DaoMaster.DevOpenHelper devOpenHelper;
	private static DaoMaster daoMaster;

	public static void initDataBase(Context context, String name) {
		if(null == devOpenHelper) {
			devOpenHelper = new DaoMaster.DevOpenHelper(context, name, null);
		}
	}

	public static DaoMaster getDaoMaster(){
		if(null == devOpenHelper){
			initDataBase(BaseApplication.context(),BaseApplication.DateBaseName);
		}
		return new DaoMaster(devOpenHelper.getWritableDb());
	}

	public static DaoSession getDaoSession(){
		if(null == daoMaster){
			daoMaster = getDaoMaster();
		}
		return daoMaster.newSession();
	}
}
