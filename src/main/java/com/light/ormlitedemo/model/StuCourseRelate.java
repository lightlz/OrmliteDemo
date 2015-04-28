package com.light.ormlitedemo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by light on 15/4/28.
 * TODO（学生课程关联表）
 */
@DatabaseTable(tableName = "student_courses")
public class StuCourseRelate {

    @DatabaseField(generatedId = true,allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(foreign = true,columnName="sId")
    private Student student;

    @DatabaseField(foreign = true,columnName="cId")
    private Course course;

    public StuCourseRelate() {
    }

    public StuCourseRelate( Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
