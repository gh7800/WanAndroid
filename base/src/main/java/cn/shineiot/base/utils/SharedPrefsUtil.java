package cn.shineiot.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by zhjh.
 */

public class SharedPrefsUtil {  
    public final static String SETTING = "info";

    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    �?要保存的value�?
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context, String key, int value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
         sp.putInt(key, value);  
         sp.commit();  
    }

    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    �?要保存的value�?
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context, String key, boolean value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
         sp.putBoolean(key, value);  
         sp.commit();  
    }  
    

    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    �?要保存的value�?
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context, String key, String value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
         sp.putString(key, value);
         sp.commit();  
    }  
    

    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    �?要保存的value�?
     * @param @param file_name    要保存到的文件名
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context, String key, String value, String file_name) {
         Editor sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE).edit();
         sp.putString(key, value);  
         sp.commit();  
    }  
    
    public static void putValue(Context context, String key, Long value) {
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putLong(key, value);  
        sp.commit();  
   } 
    
    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    获取失败，显示的默认�?
     * @return void    返回类型
     * @throws
     */
    public static int getValue(Context context, String key, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);  
        return value;  
    }

    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    获取失败，显示的默认�?
     * @return void    返回类型
     * @throws
     */
    public static boolean getValue(Context context, String key, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);  
        return value;  
    } 
    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    获取失败，显示的默认�?
     * @return void    返回类型
     * @throws
     */
    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;  
    }

    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作�?)
     * @param @param context UI上下�?
     * @param @param key	�?要保存的Key�?
     * @param @param value    获取失败，显示的默认�?
     * @param @param file_name    从改文件名的文件中取数据
     * @return void    返回类型
     * @throws
     */
    public static String getValue(Context context, String key, String defValue, String file_name ) {
    	SharedPreferences sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
    	String value = sp.getString(key, defValue);
    	return value;  
    }
    
	/**
	 * 
	 * @Title: clearData
	 * @Description: TODO(这里用一句话描述这个方法的作�?)
	 * @param @param context UI上下�?
	 * @param @param filename    �?要清空的文件�?
	 * @return void    返回类型
	 * @throws
	 */
    public static void clearData(Context context) {
    	  SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
    	  sp.edit().clear().commit();
    }


    
    /** 
    * @Title: clearDataByKey 
    * @Description: 删除哪一条数�? 
    * @param @param context
    * @param @param file_name
    * @param @param key    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static void clearDataByKey (Context context, String key ) {
    	SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
    	sp.edit().remove(key).commit();
    }




}  