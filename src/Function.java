import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author 马雯钧
 */

public class Function {
    /**
     * 向已有的Group中添加学生，键入学生的个数、姓名、ID、性别
     * @param group 已有的Group
     */
    public static void addStudentsToGroup(Group group) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("这个组加几个宝？");
        int stuNum = scanner.nextInt();
        for(int j=1;j<=stuNum;j++){
            System.out.println("请输入第" + j + "位学生的名字、ID、性别(换行以继续)：");
            String stuName = scanner.next();
            String stuId = scanner.next();
            int stuSex = scanner.nextInt();
            Student stu = new Student(stuName,stuId,stuSex);
            group.addStudent(stu);
        }
    }
    /**
     * 新建一个新组，键入组名、组长信息,先有组员才会考虑成立小组,所以必然添加组员
     * @return group 新建的组
     */
    public static @NotNull Group addGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("这个新组想叫什么");
        String groupName = scanner.next();
        System.out.println("请输入组长的名字、ID、性别(换行以继续)：");
        String leaderName = scanner.next();
        String leaderId = scanner.next();
        int leaderSex = scanner.nextInt();
        Group group = new Group(groupName,new Student(leaderName,leaderId,leaderSex));
        addStudentsToGroup(group);
        return group;
    }
    /**
     * 新建一个新班级，键入班级名、班长信息
     * 添加小组使用classGroup.addGroup(group);
     * @return classGroup 新建的班级
     */
    public static @NotNull ClassGroup InitClass() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("这个新班叫啥？");
        String className = scanner.next();
        System.out.println("请输入班长的名字、ID、性别(换行以继续)：");
        String leaderName = scanner.next();
        String leaderId = scanner.next();
        int leaderSex = scanner.nextInt();
        ClassGroup classGroup = new ClassGroup();

        classGroup.setName(className);
        classGroup.setMonitor(new Student(leaderName,leaderId,leaderSex));
        return classGroup;
    }





































//    ClassGroup classGroup = new ClassGroup();
//    public static void RandomlySelect() {
//        Group group1 = new Group("group1");
//        Student[]students={
//                new Student("zhangsan","2023015001",1),
//                new Student("lisi","2023015002",0),
//                new Student("wangwu","2023015003",1),
//                new Student("zhaoliu","2023015004",0),
//                new Student("qianqi","2023015005",1),
//                new Student("zongyi","2023015006",0),
//        };
//        for(int i = 0;i<6;i++){
//            group1.addStudent(students[i]);
//        }
//        ClassGroup classGroup = new ClassGroup();
//        classGroup.addGroup(group1);
//        System.out.println(classGroup.randomGroupName());
//        System.out.println(classGroup.randomGroupStudentName(group1));
//    }
}
