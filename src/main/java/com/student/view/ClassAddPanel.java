package com.student.view;

import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.File;

/**
 * @author mwj
 */
public class ClassAddPanel extends JPanel {
    public ClassAddPanel() {
        this.setLayout(null);
        this.setBorder(new TitledBorder(new EtchedBorder(), "新增班级"));
        JLabel lblName = new JLabel("班级名称：");
        JTextField txtName = new JTextField();
        JButton btnName = new JButton("确认");
        this.add(lblName);
        this.add(txtName);
        this.add(btnName);
        lblName.setBounds(200, 80, 100, 30);
        txtName.setBounds(200, 130, 200, 30);
        btnName.setBounds(200, 180, 100, 30);

        btnName.addActionListener(e -> {
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写班级名称", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // TODO 添加班级
                File dir = new File(Constant.FILE_PATH +"/"+ txtName.getText());
                if (!dir.exists()) {
                    boolean mkDirred = dir.mkdir();
                    if(!mkDirred){
                        JOptionPane.showMessageDialog(this, "创建班级失败", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        File file = new File(Constant.FILE_PATH + "/"+txtName.getText() + "/students.txt");
                        try {
                            boolean mkFilered = file.createNewFile();
                            if(!mkFilered){
                                JOptionPane.showMessageDialog(this, "创建学生信息文件失败", "", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "新增班级成功", "", JOptionPane.INFORMATION_MESSAGE);
                txtName.setText("");
            }
        });
    }

}
