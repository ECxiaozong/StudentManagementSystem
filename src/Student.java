
/**
 * <p>小组类，用于管理学生及其组长信息。</p>
 *
 * <p>小组包含小组名称、学生列表和组长。提供了添加、移除学生，以及获取小组信息的方法。</p>
 *
 * @author 宗意、马雯钧
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
    private int sex;

    /**
     * 获取学生学号
     * @return 学号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置学号
     * @param id 学号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取学生性别
     * @return 性别
     */
    public int getSex() {
        return sex;
    }

    /**
     * 设置学生性别
     * @param sex 性别
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 获取学生姓名
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置学生姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 无参构造
     */
    public Student() {

    }

    /**
     * 有参构造
     * @param name 姓名
     * @param id 学号
     * @param sex 性别
     */
    public Student(String name, String id, int sex) {
        this.name = name;
        this.id = id;
        this.sex = sex;
    }
}
