/**
 * @author 马雯钧
 */

public class Main {
    public static void main(String[] args) {
        ClassGroup classGroup = new ClassGroup();
        classGroup.defaultClassTemplate();

        System.out.println(classGroup.randomGroupName());
        System.out.println(classGroup.randomStudentName());
    }
}
