import java.util.ArrayList;

/**
 * @author 宗意
 */
public class ClassGroup {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级学生列表
     */
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * 班级小组列表
     */
    private ArrayList<Group> groups = new ArrayList<>();

    public ClassGroup() {
    }

    public ClassGroup(String name) {
        this.name = name;
    }

    public ClassGroup(String name, ArrayList<Student> students, ArrayList<Group> groups) {
        this.name = name;
        this.students = students;
        this.groups = groups;
    }

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
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            for (Student student : group.getStudents()) {
                if (!students.contains(student)) {
                    students.add(student);
                }
            }
        }
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        for (Student student : group.getStudents()) {
            students.remove(student);
        }
    }
}