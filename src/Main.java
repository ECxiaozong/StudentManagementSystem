/**
 * @author 马雯钧
 */

public class Main {
    public static void main(String[] args) {
        /*
            新建三个班级
         */
        ClassGroup class1 = Function.InitClass();
        ClassGroup class2 = Function.InitClass();
        ClassGroup class3 = Function.InitClass();
        /*
            新建四个组
            输入各组信息
         */
        Group group1 = Function.addGroup();
        Group group2 = Function.addGroup();
        Group group3 = Function.addGroup();
        Group group4 = Function.addGroup();
        /*
            将组加入各自班级
         */
        class1.addGroup(group1);
        class2.addGroup(group2);
        class2.addGroup(group3);
        class3.addGroup(group4);
        /*
            呀!新来了个同学，叫孙八,111,男，他加入组3（组三属于二班）
         */
        Student student1 = new Student("孙八","111",1);
        group3.addStudent(student1);
        /*
            随机选人
         */
        System.out.println(class1.randomStudentName());
        /*
            随机选组
         */
        System.out.println(class1.randomGroupName());
        /*
            随机组内选人
         */
        class1.randomGroupStudentName(group1);
    }
}
