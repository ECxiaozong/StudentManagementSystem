package com.student.view;

import com.student.entity.Group;
import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentAddPanel extends JPanel {
    public StudentAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增学生"));
        JLabel lblId = new JLabel("学号：");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("姓名：");
        JTextField txtName = new JTextField();
        JLabel sexName = new JLabel("性别：");
        JComboBox<String> cmbSex = new JComboBox<>();
        cmbSex.addItem("男");
        cmbSex.addItem("女");
        JLabel lblGroup = new JLabel("小组:");
        JComboBox<String> cmbGroup = new JComboBox<>();
        JButton btnName = new JButton("确认");
        this.add(lblId);
        this.add(txtId);
        this.add(lblName);
        this.add(txtName);
        this.add(sexName);
        this.add(cmbSex);
        this.add(lblGroup);
        this.add(cmbGroup);
        this.add(btnName);
        lblId.setBounds(200, 50, 100, 30);
        txtId.setBounds(200, 90, 100, 30);
        lblName.setBounds(200, 130, 100, 30);
        txtName.setBounds(200, 170, 200, 30);
        sexName.setBounds(200,210,200,30);
        cmbSex.setBounds(200,250,200,30);
        lblGroup.setBounds(200, 290, 100, 30);
        cmbGroup.setBounds(200, 330, 100, 30);
        btnName.setBounds(200, 370, 100, 30);

        File folder = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if(file.isFile()&&!("students.txt".equals(file.getName()))){
                    String groupName = file.getName();
                    groupName = groupName.substring(0,groupName.lastIndexOf("."));
                    cmbGroup.addItem(groupName);
                }
            }
        }

        btnName.addActionListener(e -> {
            if (txtId.getText() == null || txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学号", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学生姓名", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String stu = txtName.getText()+","+txtId.getText()+","+cmbSex.getSelectedItem()+"," + "0";
            try {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" + "students.txt", true))) {
                    bw.write(stu);
                    bw.newLine();
                }
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" +cmbGroup.getSelectedItem() + ".txt", true))){
                    bw.write(stu);
                    bw.newLine();
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
                System.out.println("写入文件时发生错误。");
            }
            JOptionPane.showMessageDialog(this, "添加学生成功", "", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
