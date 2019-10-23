package cn.shineiot.base.utils;

import android.util.Log;
import android.widget.Toast;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;

import java.util.List;

import cn.shineiot.base.BaseApplication;

/**
 * 单例
 *
 * @author GF63
 */
public class DBUtil {

    private static DBUtil greenDaoUtil = null;

    public static DBUtil getInstance() {
        if (null == greenDaoUtil) {
            synchronized (DBUtil.class) {
                if (null == greenDaoUtil) {
                    greenDaoUtil = new DBUtil();
                }
            }
        }
        return greenDaoUtil;
    }

    /**
     * 插入
     */
    public <T> boolean insert(T t) {
        boolean flag = false;
        try {
            DBHelper.getDaoSession().insert(t);
            flag = true;
        } catch (Error error) {
            Log.e("tag",error.getMessage());
            flag = false;
            Toast.makeText(BaseApplication.context(),"保存失败"+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    /**
     * 插入所有
     * @param list
     * @param <T>
     * @return
     */
    public <T> boolean insertAll(final List<T> list) {
        boolean flag = false;
        try {
            DBHelper.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0,size = list.size(); i < size; i++) {
                        DBHelper.getDaoSession().insert(list.get(i));
                    }
                }
            });
            flag = true;
        } catch (Error error) {
            flag = false;
            Toast.makeText(BaseApplication.context(),"保存失败"+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return flag;
    }


    /**
     * 查询单条数据
     * @param abstractDao
     * @param property
     * @param value
     * @return
     */
    public Object query(AbstractDao abstractDao, Property property, String value){
        return abstractDao.queryBuilder().where(property.eq(value)).build().unique();
    }

    /**
     * 查询所有
     */
    public List queryList(AbstractDao abstractDao){
        return abstractDao.loadAll();
    }

    /**
     * 模糊查询 如下：
     * @param cls userDao
     * @param property UserDao.Properties.Name 查询的KEY
     * @param msg 查询的value
     */
    public List queryList_Like(AbstractDao cls, Property property, String msg){
        return cls.queryBuilder().where(property.like("%"+msg+"%")).build().list();
    }

    /**
     * 查询（key = value）的集合
     */
    public List queryList_Eq(AbstractDao cls, Property property, String msg){
        return cls.queryBuilder().where(property.eq(msg)).build().list();
    }

    /**
     * 更新数据
     * @param cls 更新的对象
     */
    public void update(AbstractDao abstractDao, Object cls){
        abstractDao.update(cls);
    }

    /**
     * 删除一条数据
     */
    public void delete(AbstractDao abstractDao, Object obj){
        abstractDao.delete(obj);
    }

    /**
     * 删除一个表
     */
    public void deleteTable(AbstractDao abstractDao){
        abstractDao.deleteAll();
    }

}
