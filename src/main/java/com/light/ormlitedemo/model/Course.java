package com.light.ormlitedemo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by light on 15/4/25.
 * TODO(选修课程)
 */
@DatabaseTable(tableName = "course")
public class Course implements Serializable{

    @DatabaseField(generatedId = true,allowGeneratedIdInsert = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private int courseId;
    @DatabaseField(canBeNull = false)
    private String courseName;
    @DatabaseField(canBeNull = false)
    private String courseLoc;

    public Course() {

    }

    public Course(int courseId, String courseName, String courseLoc) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseLoc = courseLoc;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLoc() {
        return courseLoc;
    }

    public void setCourseLoc(String courseLoc) {
        this.courseLoc = courseLoc;
    }
}
