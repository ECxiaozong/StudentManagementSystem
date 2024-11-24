import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * <p>小组包含小组名称、学生列表和组长。提供了添加、移除学生，以及获取小组信息的方法。</p>
 *
 * @author 宗意
 * @since 2024/10/15
 * @version 1.1
 */

public class Group {
    /**
     * 小组名称
     */
    private String name;

    /**
     * 小组成员列表
     */
    private ArrayList<Student> students = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, ArrayList<Student> students) {
        this.name = name;
        this.students = students;
    }

    public int getNumber() {
        return students.size();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                break;
            }
        }
    }

    public void removeStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                students.remove(student);
                break;
            }
        }
    }

    public Student randomStudent() {
        int index = (int) (Math.random() * students.size());
        return students.get(index);
    }
}
