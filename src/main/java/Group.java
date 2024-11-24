import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * <p>小组类，用于管理学生及其组长信息。</p>
 *
 * <p>小组包含小组名称、学生列表和组长。提供了添加、移除学生，以及获取小组信息的方法。</p>
 *
 * @author 宗意、马雯钧
 * @since 2024/10/15
 * @version 1.1
 */

public class Group {



    /**
     * 小组名称
     */
    private String name;

    /**
     * 小组学生列表
     */
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * 组长
     */
    private Student leader;

    /**
     * 获取小组名称
     * @return 小组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置小组名称
     * @param name 小组名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 查询小组人数
     * @return 小组人数
     */
    public int getGroupSize() {
        return students.size();
    }

    /**
     * 获取学生列表
     * @return 学生列表
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * 设置学生列表
     * @param students 学生列表
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * 向小组学生列表中添加学生
     * @param student 学生
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * 移除小组学生
     * @param student 学生
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * 移除学生根据姓名
     * @param name 学生姓名
     */
    public void removeStudentName(String name)
    {
        students.removeIf(student -> student.getName().equals(name));
    }

    /**
     * 移除学生根据学号
     * @param id 学生学号
     */
    public void removeStudentId(String id)
    {
        students.removeIf(student -> student.getId().equals(name));
    }

    /**
     * 获取组长
     * @return 组长
     */
    public Student getLeader() {
        if (leader == null) {
            return students.get(0);
        }
        return leader;
    }

    /**
     * 获取组长姓名
     * @return 组长姓名
     */
    public String getLeaderName() {
        return getLeader().getName();
    }

    /**
     * 设置组长
     * @param leader 组长
     */
    public void setLeader(Student leader) {
        this.leader = leader;
    }

    /**
     * 设置组长根据姓名
     * @param leaderName 组长姓名
     */
    public void setLeaderName(String leaderName) {
        for (Student student : students) {
            if (student.getName().equals(leaderName)) {
                leader = student;
            }
        }
    }

    /**
     * 设置组长根据学号
     * @param leaderId 组长学号
     */
    public void setLeaderId(String leaderId) {
        for (Student student : students) {
            if (student.getId().equals(leaderId)) {
                leader = student;
            }
        }
    }

    /**
     * 根据学生列表构造小组
     * @param students 学生列表
     */
    public Group(@NotNull ArrayList<Student> students) {
        this.name = students.get(0).getName() + "的小组";
        this.students = students;
        this.leader = students.get(0);
    }

    /**
     * 根据小组名称构造小组
     */
    public Group(String name) {
        this.name = name;
    }

    /**
     * 根据小组名称和组长构造小组
     */
    public Group(String name, Student leader) {
        this.name = name;
        this.students.add(leader);
        this.leader = leader;
    }

    /**
     * 根据小组名称、学生列表构造小组
     * @param name 小组名称
     * @param students 学生列表
     */
    public Group(String name, ArrayList<Student> students) {
        this.name = name;
        this.students = students;
        this.leader = students.get(0);
    }

    public Group(String name, ArrayList<Student> students, Student leader) {
        this.name = name;
        this.students = students;
        this.leader = leader;
    }

    public Group(Student leader)
    {
        this.leader = leader;
        this.name = leader.getName() + "的小组";
        this.students.add(leader);
    }

    /**
     * 打印小组信息
     */
    public void printGroup()
    {
        System.out.println("Name: " + name);
        System.out.println("Group Number: " + getGroupSize());
        System.out.println("Leader: " + getLeaderName());
        System.out.println("Students: ");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    /**
     * 随机选取一个学生
     * @return 随机选取的学生
     */
    public Student randomStudent() {
        int index = (int) (Math.random() * getGroupSize());
        return students.get(index);
    }

    public String randomStudentName() {
        int index = (int) (Math.random() * getGroupSize());
        return students.get(index).getName();
    }
}
