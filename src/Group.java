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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupSize() {
        return students.size();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

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
