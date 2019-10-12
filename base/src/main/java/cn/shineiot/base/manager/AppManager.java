
package cn.shineiot.base.manager;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;


/**
 * @author GF63
 */
public class AppManager {


	public  Stack<Activity> activityStack;

	private static AppManager instance;

	private AppManager() {
	}

	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

    public void removeActivity(Activity activity) {

        if (activity != null) {

            activityStack.remove(activity);

            activity = null;

        }
    }
	/**
	 * 获取指定的Activity
	 */
	public  Activity getActivity(Class<?> cls) {
		if (activityStack != null) {
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		}
		return null;
	}
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	public void finishActivity(Activity activity) {

		if (activity != null) {

			if(activity.isFinishing()) {
				activityStack.remove(activity);

//				activity.finish();
				activity = null;
			}else {
				activityStack.remove(activity);

				activity.finish();
				activity = null;
			}

		}
	}

	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {

				finishActivity(activity);
				break;
			}
		}
	}

	public void finishAllActivityAndExit(Context context) {
		if (null != activityStack) {
			for (int i = 0, size = activityStack.size(); i < size; i++) {
				if (null != activityStack.get(i)) {
					activityStack.get(i).finish();
				}
			}
			activityStack.clear();
		}
	}

}