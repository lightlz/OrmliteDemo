package com.light.ormlitedemo.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.light.ormlitedemo.model.Course;
import com.light.ormlitedemo.model.StuCourseRelate;
import com.light.ormlitedemo.model.Student;

import java.sql.SQLException;
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

    /**
     * TODO(批量插入学生课表)
     * @param context
     * @param stuCourseRelateList
     * @return
     */
    public static void insertStuCourse(Context context,List<StuCourseRelate> stuCourseRelateList){

        RuntimeExceptionDao<StuCourseRelate, Integer> simpleDao = DatabaseHelper.getHelper(context)
                .getSimpleStuCourseDao();

        for (int i=0;i<stuCourseRelateList.size();i++){
             simpleDao.create(stuCourseRelateList.get(i));
        }
    }

    /**
     * TODO(插入课程)
     * @param context
     * @param course
     */
    public static void insertCourse(Context context,Course course){

        RuntimeExceptionDao<Course, Integer> simpleCourseDao = DatabaseHelper.getHelper(context)
                .getSimpleCourseDao();

        simpleCourseDao.create(course);
    }


    /**
     * TODO(获取学生课程)
     * @param context
     * @param student
     * @return
     * @throws SQLException
     */
    public static List<Course> queryCourse(Context context,Student student) throws SQLException {
        List<Course> list = null;
        RuntimeExceptionDao<StuCourseRelate, Integer> simpleStuCourseDao = DatabaseHelper.getHelper(context)
                .getSimpleStuCourseDao();
        RuntimeExceptionDao<Course, Integer> simpleCourseDao = DatabaseHelper.getHelper(context)
                .getSimpleCourseDao();
        /**
         select * from `course` where `cId` in {
            select `cId` from `student_courses` where `sId` = ?
         }
         **/
        //内
        QueryBuilder<StuCourseRelate, Integer> stuCourseQueryBuilder = simpleStuCourseDao.queryBuilder();
        //select column : cId
        stuCourseQueryBuilder.selectColumns("cId");
        //where sId = ?
        SelectArg userSelectArg = new SelectArg();
        stuCourseQueryBuilder.where().eq("sId", userSelectArg);
        //外
        QueryBuilder<Course, Integer> couseBuilder = simpleCourseDao.queryBuilder();
        //where in
        PreparedQuery<Course> qurey = couseBuilder.where().in("cId",stuCourseQueryBuilder).prepare();
        //index of the holder
        qurey.setArgumentHolderValue(0,student);
        list = simpleCourseDao.query(qurey);
        return list;
    }
}
