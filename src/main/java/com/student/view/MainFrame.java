package com.student.view;

import com.student.util.Constant;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        this.getContentPane().setLayout(new BorderLayout());
        initMenus();

        this.setSize(600, 500);
        // 设置窗口居中
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initMenus() {
        JMenuBar mainMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem changeClassMenuItem = new JMenuItem("切换当前班");
        JMenuItem exportScoreMenuItem = new JMenuItem("导出当前班成绩");
        JMenuItem exitMenuItem = new JMenuItem("退出");

        JMenu classMenu = new JMenu("班级管理");
        JMenuItem addClassMenuItem = new JMenuItem("新增班级");
        JMenuItem classListMenuItem = new JMenuItem("班级列表");

        JMenu groupMenu = new JMenu("小组管理");
        JMenuItem addGroupMenuItem = new JMenuItem("新增小组");
        JMenuItem groupListMenuItem = new JMenuItem("小组列表");

        JMenu studentMenu = new JMenu("学生管理");
        JMenuItem addStudentMenuItem = new JMenuItem("新增学生");
        JMenuItem studentListMenuItem = new JMenuItem("学生列表");

        JMenu onClassMenu = new JMenu("课堂管理");
        JMenuItem randomGroupMenuItem = new JMenuItem("随机小组");
        JMenuItem randomStudentMenuItem = new JMenuItem("随机学生");

        this.getContentPane().add(mainMenu, BorderLayout.NORTH);
        mainMenu.add(fileMenu);
        mainMenu.add(classMenu);
        mainMenu.add(groupMenu);
        mainMenu.add(studentMenu);
        mainMenu.add(onClassMenu);
        fileMenu.add(changeClassMenuItem);
        fileMenu.add(exportScoreMenuItem);
        fileMenu.add(exitMenuItem);
        classMenu.add(addClassMenuItem);
        classMenu.add(classListMenuItem);
        groupMenu.add(addGroupMenuItem);
        groupMenu.add(groupListMenuItem);
        studentMenu.add(addStudentMenuItem);
        studentMenu.add(studentListMenuItem);
        onClassMenu.add(randomGroupMenuItem);
        onClassMenu.add(randomStudentMenuItem);
        // 添加菜单事件
        // 切换班级
        changeClassMenuItem.addActionListener(e -> {
            this.getContentPane().removeAll();
            initMenus();
            ChangeClassPanel changeClassPanel = new ChangeClassPanel(this);
            this.getContentPane().add(changeClassPanel, BorderLayout.CENTER);
            this.getContentPane().validate();
        });
//         导出成绩
        exportScoreMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String[][] data = new String[Constant.classGroup.getStudents().size()][2];
                String scoreFolderPath = "D:/成绩导出";//成绩导出文件夹
                File scoreFolder = new File(scoreFolderPath);
                if (!scoreFolder.exists()) {
                    scoreFolder.mkdir();
                }
                String filePath = "D:/成绩导出"+"/"+Constant.CLASS_PATH+".txt";//成绩导出文件
                File file = new File(filePath);

                try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
                    String studentsInformationPath = Constant.FILE_PATH + "/"+ Constant.CLASS_PATH+"/students.txt";//学生信息文件
                    file.createNewFile();
                    try(BufferedReader br = new BufferedReader(new FileReader(studentsInformationPath))){
                        String line;
                        int index = 0;
                        while((line = br.readLine()) != null){
                            String[] split = line.split(",");
                            data[index][0] = split[0];
                            data[index][1] = split[3];
                            index++;
                        }
                    }
                    for(String[] student : data){
                        bw.write(student[0] + ":" + student[1] + "分"+"\n");
                        bw.newLine();
                    }
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "成绩已导出", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
//         退出程序
        exitMenuItem.addActionListener(e -> System.exit(0));
        // 新增班级
        addClassMenuItem.addActionListener(e -> {
            this.getContentPane().removeAll();
            initMenus();
            ClassAddPanel classAddPanel = new ClassAddPanel();
            this.getContentPane().add(classAddPanel, BorderLayout.CENTER);
            this.getContentPane().validate();
        });
        // 班级列表
        classListMenuItem.addActionListener(e -> {
            this.getContentPane().removeAll();
            initMenus();
            ClassListPanel classListPanel = new ClassListPanel();
            this.getContentPane().add(classListPanel, BorderLayout.CENTER);
            this.getContentPane().validate();
        });
        // 新增小组
        addGroupMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                this.getContentPane().add(new GroupAddPanel(), BorderLayout.CENTER);
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
        // 小组列表
        groupListMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                try {
                    this.getContentPane().add(new GroupListPanel(), BorderLayout.CENTER);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
        // 新增学生
        addStudentMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                this.getContentPane().add(new StudentAddPanel(), BorderLayout.CENTER);
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
        // 学生列表
        studentListMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                this.getContentPane().add(new StudentListPanel(), BorderLayout.CENTER);
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
        // 随机抽取小组
        randomGroupMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                this.getContentPane().add(new RandomGroupPanel(), BorderLayout.CENTER);
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
        // 随机抽取学生
        randomStudentMenuItem.addActionListener(e -> {
            if (Constant.CLASS_PATH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.getContentPane().removeAll();
                initMenus();
                this.getContentPane().add(new RandomStudentPanel(), BorderLayout.CENTER);
                this.getContentPane().repaint();
                this.getContentPane().validate();
            }
        });
    }
}
