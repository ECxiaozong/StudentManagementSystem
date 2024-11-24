import java.util.ArrayList;

public class ClassGroup {
    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级学生列表
     */
    private ArrayList<Student> students = new ArrayList<Student>();

    /**
     * 班级小组列表
     */
    private ArrayList<Group> Groups = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Group> getGroups() {
        return Groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        Groups = groups;
    }



}