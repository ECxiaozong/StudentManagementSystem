import javax.swing.*;
import java.awt.*;

/**
 * @author 宗意
 */

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("学生管理系统");
        int size = 5000;
        frame.setSize(size / 4, size / 6);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(size, size);

        JMenu fileMenu = new JMenu("文件");
        fileMenu.setFont(new Font("微软雅黑", Font.BOLD, size / 200));
        JMenuItem switchCurrentClassItem = new JMenuItem("切换当前班级");
        JMenuItem exportCurrentClassGradeItem = new JMenuItem("导出当前班级成绩");
        JMenuItem exitItem = new JMenuItem("退出");
        switchCurrentClassItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        exportCurrentClassGradeItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        exitItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        fileMenu.add(switchCurrentClassItem);
        fileMenu.add(exportCurrentClassGradeItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        JMenu classManagementMenu = new JMenu("班级管理");
        classManagementMenu.setFont(new Font("微软雅黑", Font.BOLD, size / 200));
        JMenuItem addClassItem = new JMenuItem("新增班级");
        JMenuItem classListItem = new JMenuItem("班级列表");
        addClassItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        classListItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        classManagementMenu.add(addClassItem);
        classManagementMenu.add(classListItem);
        menuBar.add(classManagementMenu);

        JMenu groupManagementMenu = new JMenu("小组管理");
        groupManagementMenu.setFont(new Font("微软雅黑", Font.BOLD, size / 200));
        JMenuItem addGroupItem = new JMenuItem("新增小组");
        JMenuItem groupListItem = new JMenuItem("小组列表");
        addGroupItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        groupListItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        groupManagementMenu.add(addGroupItem);
        groupManagementMenu.add(groupListItem);
        menuBar.add(groupManagementMenu);

        JMenu studentManagementMenu = new JMenu("学生管理");
        studentManagementMenu.setFont(new Font("微软雅黑", Font.BOLD, size / 200));
        JMenuItem addStudentItem = new JMenuItem("新增学生");
        JMenuItem studentListItem = new JMenuItem("学生列表");
        addStudentItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        studentListItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        studentManagementMenu.add(addStudentItem);
        studentManagementMenu.add(studentListItem);
        menuBar.add(studentManagementMenu);

        JMenu classroomManagementMenu = new JMenu("课堂管理");
        classroomManagementMenu.setFont(new Font("微软雅黑", Font.BOLD, size / 200));
        JMenuItem randomGroupItem = new JMenuItem("随机小组");
        JMenuItem randomStudentItem = new JMenuItem("随机学生");
        randomGroupItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        randomStudentItem.setFont(new Font("微软雅黑", Font.PLAIN, size / 250));
        classroomManagementMenu.add(randomGroupItem);
        classroomManagementMenu.add(randomStudentItem);
        menuBar.add(classroomManagementMenu);

        frame.setVisible(true);
    }
}
