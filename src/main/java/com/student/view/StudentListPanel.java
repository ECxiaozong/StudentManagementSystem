package com.student.view;

import com.student.entity.Group;
import com.student.entity.Student;
import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentListPanel extends JPanel {
    String[] headers = {"学号", "姓名", "分数"};
    String[][] data = null;
    JTable studentTable;
    JTextField txtId = new JTextField();
    JTextField txtName = new JTextField();
    JComboBox<String> cmbGroup = new JComboBox<>();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");

    public StudentListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "学生列表"));
        this.setLayout(new BorderLayout());
        File file = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt");
        BufferedReader reader = null;
        int count = 0;
        String line = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data = new String[count][3];
        count = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if ((line = reader.readLine()) == null) {
                    reader.close();
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] str = line.split(",");
            data[count][0] = str[1];
            data[count][1] = str[0];
            data[count][2] = str[3];
//            System.out.println(count);
            count++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        studentTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtId);
        txtId.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(cmbGroup);
        cmbGroup.setPreferredSize(new Dimension(100, 30));
        cmbGroup.addItem("请选择小组");
        file = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH);
        File files[] = file.listFiles();
        for (File file1 : files) {
            if (!"students.txt".equals(file1.getName())) {
                cmbGroup.addItem(file1.getName().substring(0, file1.getName().length() - 4));
            }
        }

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                txtId.setText(data[selectedRow][0]);
                txtName.setText(data[selectedRow][1]);
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtId.getText() == null || txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学号", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写姓名", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // TODO 修改学生
            Student student = new Student();
            student.setId(txtId.getText());
            student.setName(txtName.getText());
            student.setScore(Integer.parseInt(data[selectedRow][2]));
            String group = (String) cmbGroup.getSelectedItem() + ".txt";
            File file1 = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" + group);
            if (!file1.exists()) {
                JOptionPane.showMessageDialog(this, "请选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            BufferedReader reader1 = null;

            try {
                // 使用 FileWriter 以追加模式打开文件
                FileWriter writer = new FileWriter(file1, true);
                // 写入学生信息
                writer.write(student.getName() + "," + student.getId() + ",0" + student.getScore() + "\n");
                // 关闭 writer
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "写入文件失败", "", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
        });
        btnDelete.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "删除学生会删除他的考勤、成绩等，确认删除？", "", JOptionPane.YES_NO_OPTION) != 0) {
                return;
            }
            // TODO 删除学生
            String studentId = data[selectedRow][0];
            File file1 = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt");
            ArrayList<String> lines = new ArrayList<>();
            try {
                BufferedReader reader1 = new BufferedReader(new FileReader(file1));
                String newLine = null;
                String[] str = null;
                while ((newLine = reader1.readLine()) != null) {
                    str = newLine.split(",");
                    if (!studentId.equals(str[1])) {
                        lines.add(newLine);
                    }
                }
                reader1.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // 写入修改后的文件
            try {
                FileWriter writer = new FileWriter(file1, false);
                for (String line2 : lines) {
                    writer.write(line2 + "\n");
                }
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "写入文件失败", "", JOptionPane.ERROR_MESSAGE);
            }
            // 删除小组文件中的学生信息
            String group = (String) cmbGroup.getSelectedItem() + ".txt";
            File file2 = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" + group);
            ArrayList<String> lines2 = new ArrayList<>();
            try {
                BufferedReader reader2 = new BufferedReader(new FileReader(file2));
                String newLine2 = null;
                String[] str2 = null;
                while ((newLine2 = reader2.readLine()) != null) {
                    str2 = newLine2.split(",");
                    if (!studentId.equals(str2[1])) {
                        lines2.add(newLine2);
                    }
                }
                reader2.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // 写入修改后的文件
            try {
                FileWriter writer2 = new FileWriter(file2, false);
                for (String line3 : lines2) {
                    writer2.write(line3 + "\n");
                }

                writer2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "写入文件失败", "", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "删除学生成功", "", JOptionPane.INFORMATION_MESSAGE);
        });
    }

}
