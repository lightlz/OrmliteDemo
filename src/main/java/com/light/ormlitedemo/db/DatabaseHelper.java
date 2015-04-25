package com.light.ormlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.light.ormlitedemo.model.Course;
import com.light.ormlitedemo.model.Student;

import java.sql.SQLException;

/**
 * Created by light on 15/4/25.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    // 数据库名称
    private static final String DATABASE_NAME = "school.db";
    // 数据库版本
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Student, Integer> simpleStudentDao;

    private RuntimeExceptionDao<Course, Integer> simpleCourseDao;


    public DatabaseHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DatabaseHelper getHelper(Context context){

        DatabaseHelper helper;
        helper = new DatabaseHelper(context);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, Student.class);
            TableUtils.createTable(connectionSource,Course.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

    }

    public RuntimeExceptionDao<Student, Integer> getSimpleStudentDao() {

        if (simpleStudentDao == null) {
            simpleStudentDao = getRuntimeExceptionDao(Student.class);
        }

        return simpleStudentDao;
    }

    public RuntimeExceptionDao<Course, Integer> getSimpleCourseDao() {

        if (simpleCourseDao == null) {
            simpleCourseDao = getRuntimeExceptionDao(Course.class);
        }

        return simpleCourseDao;
    }


}
