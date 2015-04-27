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

    /**
     * TODO(查询学生所以数据)
     * @param context
     * @return
     */
    public static List<Student> queryStudent(Context context){

        RuntimeExceptionDao<Student, Integer> simpleStudentDao = DatabaseHelper.getHelper(context)
                .getSimpleStudentDao();

        List<Student> list = simpleStudentDao.queryForAll();
        return list;
    }

    /**
     * TODO(更新单个学生数据)
     * @param context
     * @param key
     * @param value
     * @param where
     * @return
     */
    public  static int updateStudent(Context context,String key,String value,int where){
        int ret = -1;
        RuntimeExceptionDao<Student, Integer> simpleStudentDao = DatabaseHelper.getHelper(context)
                .getSimpleStudentDao();
        ret = simpleStudentDao.updateRaw("UPDATE `student` SET "
                        + key + "= '"+value+"' WHERE stuId = "+where+";");
        return ret;
    }

    /**
     * TODO(删除一个学生)
     * @param context
     * @param student
     * @return
     */
    public static int deleteStudent(Context context,Student student){
        int ret = -1;

        RuntimeExceptionDao<Student, Integer> simpleStudentDao = DatabaseHelper.getHelper(context)
                .getSimpleStudentDao();

        ret = simpleStudentDao.delete(student);

        return ret;
    }

}
