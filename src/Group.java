import java.util.ArrayList;

/**
 * @author 宗意
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

    public void removeStudentId(String id)
    {
        students.removeIf(student -> student.getId().equals(name));
    }

    public Student getLeader() {
        return leader;
    }

    public String getLeaderName() {
        return leader.getName();
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public void setLeaderName(String leaderName) {
        for (Student student : students) {
            if (student.getName().equals(leaderName)) {
                leader = student;
            }
        }
    }

    public void setLeaderId(String leaderId) {
        for (Student student : students) {
            if (student.getId().equals(leaderId)) {
                leader = student;
            }
        }
    }

    public Group(ArrayList<Student> students) {
        this.name = students.get(0).getName() + "的小组";
        this.students = students;
        this.leader = students.get(0);
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Student leader) {
        this.name = name;
        this.students.add(leader);
        this.leader = leader;
    }

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
