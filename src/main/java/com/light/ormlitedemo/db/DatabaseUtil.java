package com.light.ormlitedemo.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.light.ormlitedemo.model.Student;

import java.util.List;

/**
 * Created by light on 15/4/25.
 * TODO(数据库操作类)
 */
public class DatabaseUtil {


    /**
     * TODO(批量插入学生数据)
     * @param context
     * @param studentList
     */
    public static void insertStudent(Context context,List<Student> studentList){

        RuntimeExceptionDao<Student, Integer> simpleStudentDao = DatabaseHelper.getHelper(context)
                .getSimpleStudentDao();

        for(int i=0;i<studentList.size();i++){
            simpleStudentDao.create(studentList.get(i));
        }

    }
}
