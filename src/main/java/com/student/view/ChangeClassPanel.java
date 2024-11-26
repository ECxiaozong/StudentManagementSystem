package com.student.view;

import com.student.entity.ClassGroup;
import com.student.entity.Student;
import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;

/**
 * @author mwj
 */
public class ChangeClassPanel extends JScrollPane {
    JLabel infoLbl = new JLabel();

    public ChangeClassPanel(MainFrame mainFrame) {
        this.setBorder(new TitledBorder(new EtchedBorder(), "新选择班级"));
        int x = 160, y = 100;
        this.setLayout(null);

        File folder = new File(Constant.FILE_PATH);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            JOptionPane.showMessageDialog(this, "请先创建班级", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ButtonGroup btnGroup = new ButtonGroup();
            for (File file : files) {
                if (file.isDirectory()) {
                    JRadioButton classRadio = new JRadioButton(file.getName());
                    btnGroup.add(classRadio);
                    this.add(classRadio);
                    classRadio.setBounds(x, y, 200, 30);
                    y += 40;
                }
            }
            JButton btnChooseClass = new JButton("确认选择班级");
            this.add(btnChooseClass);
            btnChooseClass.setBounds(x, y, 120, 30);
            btnChooseClass.addActionListener(e -> {
                Enumeration<AbstractButton> elements = btnGroup.getElements();
                boolean isSelected = false;
                while (elements.hasMoreElements()) {
                    JRadioButton btn = (JRadioButton) elements.nextElement();
                    if (btn.isSelected()) {
                        isSelected = true;
                        mainFrame.setTitle(btn.getText());
                        Constant.CLASS_PATH = btn.getText();
                        infoLbl.setText("班级：" + btn.getText() + "，班级学生总数：");
                        break;
                    }
                }
                if (isSelected) {
                    // TODO 初始化小组和学生
                    try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_PATH + "/"+ Constant.CLASS_PATH + "/students.txt"))){
                        String line;
                        Constant.students.clear();
                        Constant.classGroup = null;
                        while((line = br.readLine())!=null){
                            String[] strs = line.split(",");
                            Student stu = new Student(strs[0], strs[1], strs[2], Integer.parseInt(strs[3]));
                            Constant.students.add(stu);
                        }
                        Constant.classGroup = new ClassGroup(Constant.CLASS_PATH,Constant.students);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this, "初始化小组和学生信息失败，请检查相关文件", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    this.removeAll();
                    infoLbl.setText(infoLbl.getText() + Constant.students.size());
                    infoLbl.setBounds(160, 100, 200, 30);
                    this.add(infoLbl);
                    this.repaint();
                    this.validate();
                } else {
                    JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            this.repaint();
            this.validate();
        }
    }
}
