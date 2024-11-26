package com.student.view;

import com.student.entity.Group;
import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mwj
 */
public class GroupListPanel extends JPanel {
    String[] headers = {"序号", "小组名称", "分数"};
    String[][] data = null;
    JTable classTable;
    JTextField txtName = new JTextField();
    JTextField txtScore = new JTextField();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");

    public GroupListPanel() throws FileNotFoundException {
        this.setBorder(new TitledBorder(new EtchedBorder(), "小组列表"));
        this.setLayout(new BorderLayout());
        // TODO 列举小组
        File file = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH);
        File[]groups = file.listFiles();
        data = new String[groups.length-1][3];
        int index = 0;
        for(int i= 0;i<groups.length;i++) {
            if ("students.txt".equals(groups[i].getName())) {
                continue;
            }
            data[index][0] = String.valueOf(index + 1);
            data[index][1] = groups[index].getName().replace(".txt", "");
            int score = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" + groups[i].getName()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split(",");
                    if (split.length == 4) {
                        score += Integer.parseInt(split[3]);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            data[index][2] = String.valueOf(score);
            index++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        classTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        classTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(classTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(txtScore);
        txtScore.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        classTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if(selectedRow >= 0) {
                txtName.setText(data[selectedRow][1]);
                txtScore.setText(data[selectedRow][2]);
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if(selectedRow < 0){
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(txtName.getText().isEmpty() || txtScore.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "请填写完整信息", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            File oldFile = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH+"/"+data[selectedRow][1]+".txt");
            File newFile = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH+"/"+txtName.getText()+".txt");
            if(newFile.exists()){
                JOptionPane.showMessageDialog(this, "班级名已存在", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            oldFile.renameTo(newFile);
            JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
            data[selectedRow][1] = txtName.getText();
            data[selectedRow][2] = txtScore.getText();
            DefaultTableModel tableModel1 = (DefaultTableModel) classTable.getModel();
            tableModel1.setDataVector(data, headers);
            classTable.updateUI();
            txtName.setText("");
            txtScore.setText("");
            classTable.clearSelection();
        });
        btnDelete.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if(selectedRow < 0){
                JOptionPane.showMessageDialog(this, "请先选择班级", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(JOptionPane.showConfirmDialog(this, "您确定要删除这个小组吗？", "", JOptionPane.YES_NO_OPTION) != 0){
                return;
            }
            File file1 = new File(Constant.FILE_PATH+"/"+Constant.CLASS_PATH+"/"+data[selectedRow][1]+".txt");
            file1.delete();
            // 创建一个新的数组，长度为data的长度减1
            String[][] newData = new String[data.length - 1][data[0].length];
            // 复制原数据，跳过被删除的行
            for (int i = 0, j = 0; i < data.length; i++) {
                if (i != selectedRow) {
                    newData[j++] = data[i];
                }
            }
            // 更新data为新的数组
            data = newData;
            DefaultTableModel tableModel1 = (DefaultTableModel) classTable.getModel();
            tableModel1.setDataVector(data, headers);
            classTable.updateUI();
            txtName.setText("");
            txtScore.setText("");
            classTable.clearSelection();
            JOptionPane.showMessageDialog(this, "删除小组成功", "", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
