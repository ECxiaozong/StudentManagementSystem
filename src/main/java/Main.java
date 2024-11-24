/**
 * @author 宗意
 */

public class Main {
    public static void main(String[] args) {
        ClassGroup classGroup = new ClassGroup();
        // 初始化全班学生模版
        classGroup.defaultClassTemplate();

        // 打印小组
        classGroup.printGroups();
        // 打印全班
        classGroup.printStudents();

        // 随机在全班抽取一个小组
        System.out.println(classGroup.randomGroupName());

        // 随机在全班抽取一个学生
        System.out.println(classGroup.randomStudentName());

        // 根据小组名字指定在小组中抽取
        System.out.println(classGroup.randomGroupStudentName("小组1"));
    }
}
