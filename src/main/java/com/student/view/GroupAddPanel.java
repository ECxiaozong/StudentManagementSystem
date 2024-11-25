package com.student.view;

import com.student.entity.Student;
import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupAddPanel extends JScrollPane {
    public GroupAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增小组"));
        JLabel lblName = new JLabel("小组名称：");
        JTextField txtName = new JTextField();
        JButton btnName = new JButton("确认");
        JButton diskSellBtn = new JButton("显示选中学生");
        ArrayList<String> students = new ArrayList<>();
        //多选下拉框
        //获取学生列表
        try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_PATH + "/"+ Constant.CLASS_PATH + "/students.txt"))){
            String line;
            while((line = br.readLine())!=null){
                String[] strs = line.split(",");
                students.add(strs[0]);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "初始化小组和学生信息失败，请检查相关文件", "", JOptionPane.INFORMATION_MESSAGE);
        }

        String[] array = new String[students.size()];
        JList<String>list = new JList<>(students.toArray(array));
        JScrollPane scrollPane = new JScrollPane(list);
        //显示选中学生
        diskSellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedOptions = new StringBuilder("你选择了: ");
                for (String option : list.getSelectedValuesList()) {
                    selectedOptions.append(option).append(" ");
                }
                JOptionPane.showMessageDialog(null,selectedOptions.toString());
            }
        });

        this.add(scrollPane);
        this.add(diskSellBtn);
        this.add(lblName);
        this.add(txtName);
        this.add(btnName);

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setBounds(200, 220, 100, 200);
        diskSellBtn.setBounds(320,220,100,30);
        lblName.setBounds(200, 80, 100, 30);
        txtName.setBounds(200, 130, 200, 30);
        btnName.setBounds(200, 180, 100, 30);

        btnName.addActionListener(e -> {
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写小组名称", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // TODO 新增小组
                File file = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH + "/" + txtName.getText()+".txt");
                if (file.exists()) {
                    JOptionPane.showMessageDialog(this, "小组名称已存在", "", JOptionPane.INFORMATION_MESSAGE);
                    txtName.setText("");
                    return;
                }

                try {
                    file.createNewFile();
                    ArrayList<String>selectedStudents = new ArrayList<>();
                    for (String student : list.getSelectedValuesList()) {
                        Student s = Constant.classGroup.getStudentByName(student);
                        selectedStudents.add(s.getName()+","+s.getId()+","+s.getSex()+","+s.getScore());
                    }
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constant.FILE_PATH+"/"+Constant.CLASS_PATH+"/"+txtName.getText()+".txt"))){
                        for(String student:selectedStudents){
                            bw.write(student);
                            bw.newLine();
                        }
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                        System.out.println("写入文件时发生错误。");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "新增小组成功", "", JOptionPane.INFORMATION_MESSAGE);
                txtName.setText("");
            }
        });
    }

}
