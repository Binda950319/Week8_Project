package com.wbh.week8_project.mysql;

import android.content.Context;
import android.text.TextUtils;

import com.wbh.week8_project.javabean.Component;
import com.wbh.week8_project.javabean.ComponentDao;
import com.wbh.week8_project.javabean.DaoMaster;
import com.wbh.week8_project.javabean.DaoSession;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class MyDBManager {
    ComponentDao componentDao;
    private static MyDBManager myDBManager = null;

    public MyDBManager(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, "component");
        componentDao = daoSession.getComponentDao();

    }

    public static MyDBManager getInstance(Context context) {
        if (myDBManager == null) {
            myDBManager = new MyDBManager(context);
        }
        return myDBManager;
    }

    public void insertData(Component com) {
        componentDao.insert(com);
    }

    public List<Component> queryAllData(String key) {
        QueryBuilder<Component> builder = componentDao.queryBuilder();
        Query<Component> build = null;
        if (!TextUtils.isEmpty(key)) {
            build = builder.where(ComponentDao.Properties.Description.like("%" + key + "%"))
                    .build();
        } else {
            //全部查询
            build = builder.build();
        }
        List<Component> list = build.list();
        return list;
    }

    public void deleteData(Component com) {
        componentDao.delete(com);
    }
}
