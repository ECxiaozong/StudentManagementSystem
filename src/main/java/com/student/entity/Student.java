package com.student.entity;

/**
 * <p>小组包含小组名称、学生列表和组长。提供了添加、移除学生，以及获取小组信息的方法。</p>
 *
 * @author 宗意
 * @since 2024-10-15
 * @version 1.0
 */

public class Student {
    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学号
     */
    private String id;

    /**
     * 性别
     */
    private String sex;

    /**
     * 分数
     */
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", score=" + score +
                '}';
    }

    public Student() {
    }

    public Student(String name, String id, String sex) {
        this.name = name;
        this.id = id;
        this.sex = sex;
    }
    public Student(String name, String id, String sex, int score){
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.score = score;
    }
}

