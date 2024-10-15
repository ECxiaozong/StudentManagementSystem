import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 类说明：班级类
 * 主要功能：
 * <p>
 *     设置班级名称、添删改查班级小组和学生、随机选取学生、随机选取小组、打印班级信息
 * </p>
 *
 * @author 宗意、马雯钧
 * @version 1.0
 * @since 2024-10-13
 */


public class ClassGroup {

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班长
     */
    private Student monitor;

    /**
     * 班级小组列表
     */
    private ArrayList<Group> groups = new ArrayList<>();

    /**
     * 班级学生列表
     */
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * 添加班级小组
     * @param group 班级小组
     */
    public void addGroup(Group group) {
        groups.add(group);
        students.addAll(group.getStudents());
    }

    /**
     * 获取班级名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置班级名称
     * @param name 班级名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取班级小组列表
     * @return 班级小组列表
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * 设置班级小组列表，并根据设置的班级小组列表更新学生列表
     * @param groups 班级小组列表
     */
    public void setGroups(@NotNull ArrayList<Group> groups) {
        this.groups = groups;
        students.clear();
        for (Group group : groups) {
            students.addAll(group.getStudents());
        }
    }

    /**
     * 获取班长
     * @return 班长
     */
    public Student getMonitor() {
        return monitor;
    }

    /**
     * 设置班长
     * @param monitor 班长
     */
    public void setMonitor(Student monitor) {
        this.monitor = monitor;
    }

    /**
     * 获取班级人数
     * @return 班级人数
     */
    public int getStudentNumber() {
        return students.size();
    }

    /**
     * 获取班级小组数量
     * @return 班级小组数量
     */
    public int getClassGroupNumber() {
        return groups.size();
    }

    /**
     * 获取班级学生列表
     * @return 班级学生列表
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * 设置班级学生列表，并根据设置的学生列表更新班级小组列表（每个学生单独一个小组）
     * @param students 班级学生列表
     */
    public void setStudents(@NotNull ArrayList<Student> students) {
        this.students = students;
        groups.clear();
        for (Student student : students) {
            this.groups.add(new Group(student));
        }
    }

    /**
     * 移除班级学生列表里的某个学生，并更新班级小组列表
     * @param student 班级学生
     */
    public void removeStudent(Student student) {
        students.remove(student);
        for (Group group : groups) {
            group.removeStudent(student);
        }
    }

    /**
     * 移除班级小组列表里的某个小组，并更新学生列表
     * @param group 班级小组
     */
    public void removeGroup(Group group) {
        groups.remove(group);
        students.removeAll(group.getStudents());
    }

    /**
     * 清空班级小组列表和学生列表
     */
    public void clear() {
        groups.clear();
        students.clear();
    }

    /**
     * 随机在班级中选取学生
     * @return 随机学生
     */
    public Student randomStudent() {
        int index = (int) (Math.random() * students.size());
        return students.get(index);
    }

    /**
     * 随机在班级中选取学生的名字
     * @return 随机学生名字
     */
    public String randomStudentName() {
        return randomStudent().getName();
    }

    /**
     * 随机选取一个班级小组
     * @return 随机班级小组
     */
    public Group randomGroup() {
        int index = (int) (Math.random() * groups.size());
        return groups.get(index);
    }

    /**
     * 随机选取一个班级小组的名字
     * @return 随机班级小组名字
     */
    public String randomGroupName() {
        int index = (int) (Math.random() * groups.size());
        return groups.get(index).getName();
    }

    /**
     * 随机选取一个班级小组的学生
     * @param group 班级小组
     * @return 随机班级小组学生
     */
    public Student randomGroupStudent(@NotNull Group group) {
        return group.randomStudent();
    }

    /**
     * 随机选取一个班级小组的学生名字
     * @param group 班级小组
     * @return 随机班级小组学生名字
     */
    public String randomGroupStudentName(@NotNull Group group) {
        return group.randomStudentName();
    }

    /**
     * 随机选取一个班级小组的学生，根据名字指定小组
     * @param name 班级小组名字
     * @return 随机班级小组学生
     */
    public Student randomGroupStudent(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group.randomStudent();
            }
        }
        return null;
    }

    /**
     * 随机选取一个班级小组的学生的名字，根据名字指定小组
     * @param name 班级小组名字
     * @return 随机班级小组学生名字
     */
    public String randomGroupStudentName(String name) {
        return randomGroupStudent(name).getName();
    }

    /**
     * 移除学生，根据名字指定，并更新班级小组
     * @param name 学生名字
     */
    public void removeStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                removeStudent(student);
            }
        }
    }

    /**
     * 移除小组，根据名字指定，并更新学生列表
     * @param name 小组名字
     */
    public void removeGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                removeGroup(group);
            }
        }
    }

    /**
     * 打印班级小组信息
     */
    public void printGroups() {
        for (Group group : groups) {
            group.printGroup();
        }
    }

    /**
     * 打印班级信息
     */
    public void printStudents() {
        System.out.println("Student number: " + students.size());
        System.out.println("Group number: " + groups.size());
        System.out.println("monitor: " + monitor.getName());
        System.out.println("Student list:");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    public void defaultClassTemplate() {
        for (int i = 0; i <= 9; i++) {
            Group group = new Group("小组" + (i + 1));
            for (int j = 0; j <= 3; j++) {
                Student student = new Student("张" + (i * 10 + j + 1), "20230158" + i + j,j & 1);
                if (i == 0 && j == 0) {
                    setMonitor(student);
                }
                group.addStudent(student);
            }
            addGroup(group);
        }
    }

}
