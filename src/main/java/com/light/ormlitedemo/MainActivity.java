package com.light.ormlitedemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.light.ormlitedemo.db.DatabaseHelper;
import com.light.ormlitedemo.db.DatabaseUtil;
import com.light.ormlitedemo.model.Course;
import com.light.ormlitedemo.model.StuCourseRelate;
import com.light.ormlitedemo.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button initBt;

    private Button updateBt;

    private List<Student> studentList = new ArrayList<Student>();

    private Button deleteBt;

    private Button courseTableBt;

    private TextView displayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        initBt = (Button)findViewById(R.id.bt_init_data);
        initBt.setOnClickListener(this);

        updateBt = (Button)findViewById(R.id.bt_update_data);
        updateBt.setOnClickListener(this);

        deleteBt=(Button)findViewById(R.id.bt_detele_data);
        deleteBt.setOnClickListener(this);

        courseTableBt = (Button)findViewById(R.id.bt_course_table);
        courseTableBt.setOnClickListener(this);

        displayTv = (TextView)findViewById(R.id.tv_display);

        refreshData();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.bt_init_data:
                //初始化数据
                initData();
                break;
            case R.id.bt_update_data:
                //更新一个性别
                updateData();
                break;
            case R.id.bt_detele_data:
                deleteData();
                break;
            case R.id.bt_course_table:
                getStudentCourses();
                break;
        }
    }

    /**
     * TODO(初始化数据)
     */
    private void initData(){

        List<StuCourseRelate> list = new ArrayList<StuCourseRelate>();

        studentList = DatabaseUtil.queryStudent(this);
        if(studentList == null || studentList.size() == 0){

            //插入学生
            Student student1 = new Student(10332,"李雷","22","男","one");
            studentList.add(student1);

            Student student2 = new Student(10333,"二狗","23","男","one");
            studentList.add(student2);

            Student student3 = new Student(10334,"铁柱","24","男","two");
            studentList.add(student3);

            Student student4 = new Student(10335,"全蛋","21","女","one");
            studentList.add(student4);

            Student student5 = new Student(10336,"大锤","23","女","one");
            studentList.add(student5);

            DatabaseUtil.insertStudent(this,studentList);

            //插入课程
            Course course1 = new Course(01,"数学","1-102");
            Course course2 = new Course(02,"语文","1-103");
            Course course3 = new Course(03,"英文","1-104");
            DatabaseUtil.insertCourse(this,course1);
            DatabaseUtil.insertCourse(this,course2);
            DatabaseUtil.insertCourse(this,course3);

            //插入学生课表
            StuCourseRelate stuCourseRelate1 = new StuCourseRelate(student1,course1);
            list.add(stuCourseRelate1);

            StuCourseRelate stuCourseRelate2 = new StuCourseRelate(student2,course2);
            list.add(stuCourseRelate2);

            StuCourseRelate stuCourseRelate3 = new StuCourseRelate(student3,course3);
            list.add(stuCourseRelate3);

            StuCourseRelate stuCourseRelate4 = new StuCourseRelate(student1,course3);
            list.add(stuCourseRelate4);

            DatabaseUtil.insertStuCourse(this,list);


        }else{
            Toast.makeText(this,"the datas had been inserted",Toast.LENGTH_SHORT).show();
        }
        refreshData();
    }

    private void updateData(){

       int ret =  DatabaseUtil.updateStudent(this,"stuSex","男",10335);
       if(ret == 1){
           Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
           refreshData();
       }else{
           Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show();
       }
    }

    private void deleteData(){

        List<Student> list = DatabaseUtil.queryStudent(this);

        if(list.size() > 0) {
            int ret = DatabaseUtil.deleteStudent(this, list.get(0));

            if (ret == 1) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                refreshData();
            } else {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void refreshData(){

        List<Student> list = DatabaseUtil.queryStudent(this);

        StringBuffer sb = new StringBuffer();

        for (int i=0;i<list.size();i++){
            sb.append(list.get(i).toString());
        }
        displayTv.setText(sb);

    }

    private void getStudentCourses(){

        try {
            studentList = DatabaseUtil.queryStudent(this);
            List<Course> list = new ArrayList<Course>();
            StringBuffer sb = new StringBuffer();

            for(int i=0;i<studentList.size();i++){
               list.clear();
               list = DatabaseUtil.queryCourse(this,studentList.get(i));
               sb.append(studentList.get(i).getStuName()+ "的课程 ：\n " + list.toString() +"\n\n");
            }
            displayTv.setText(sb);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
