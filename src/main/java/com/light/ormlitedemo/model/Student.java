package com.light.ormlitedemo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by light on 15/4/25.
 * TODO(学生表)
 */
@DatabaseTable(tableName="student")
public class Student implements Serializable {


    @DatabaseField(generatedId = true,allowGeneratedIdInsert = true,columnName = "sId")
    private int id;
    @DatabaseField(canBeNull = false)
    private int stuId;
    @DatabaseField(canBeNull = false)
    private String stuName;
    @DatabaseField()
    private String stuAge;
    @DatabaseField()
    private String stuSex;
    @DatabaseField(defaultValue = "one")
    private String stuGrade;


    public Student() {

    }

    public Student(int stuId, String stuName, String stuAge, String stuSex, String stuGrade) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuSex = stuSex;
        this.stuGrade = stuGrade;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }


    @Override
    public String toString() {

        return stuId +"  " +stuName + "  " +stuAge + "  " +stuSex +"  " +stuGrade +"\n";
    }
}
