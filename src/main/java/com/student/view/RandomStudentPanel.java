package com.student.view;

import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.*;

public class RandomStudentPanel extends JPanel {
    private JLabel lbl2 = new JLabel("学生姓名：");
    private JLabel lbl3 = new JLabel("学生照片：");
    private JLabel lblPic = new JLabel("照片");
    private JTextField txtStudent = new JTextField();
    private JButton btnChooseStudent = new JButton("随机学生");
    private JButton btnAbsence = new JButton("缺勤");
    private JButton btnLeave = new JButton("请假");
    private JButton btnAnswer = new JButton("答题");
    Thread threadStudent = null;   // 随机抽取小组的线程
    public RandomStudentPanel(){
        this.setBorder(new TitledBorder(new EtchedBorder(), "随机学生点名"));
        this.setLayout(null);
        this.add(lbl2);
        this.add(lbl3);
        this.add(txtStudent);
        this.add(lblPic);
        this.add(btnChooseStudent);
        this.add(btnAbsence);
        this.add(btnLeave);
        this.add(btnAnswer);

        lbl2.setBounds(160, 50, 100, 30);
        txtStudent.setBounds(160, 90, 130, 30);
        txtStudent.setEditable(false);
        lblPic.setBounds(160, 130, 130, 150);
        btnChooseStudent.setBounds(160, 300, 130, 30);
        btnAbsence.setBounds(160, 340, 60, 30);
        btnLeave.setBounds(230, 340, 60, 30);
        btnAnswer.setBounds(300, 340, 60, 30);

        // TODO 随机学生
        btnChooseStudent.addActionListener(e -> {

            if (e.getActionCommand().equals("停")) {
                btnChooseStudent.setText("随机学生");

            } else {
                btnChooseStudent.setText("停");
                int count = 0;
                File file = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt");
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while (br.readLine() != null) {
                        count++;
                    }
                    br.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                int finalCount = count;
                new Thread(() -> {
                    while (true) {
                        int index = (int) (Math.random() * finalCount);
                        String studentName = "";
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            while ((studentName = br.readLine()) != null) {
                                index--;
                                if (index < 0) {
                                    studentName = studentName.split(",")[0];
                                    break;
                                }
                            }
                            br.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        String finalStudentName = studentName;
                        SwingUtilities.invokeLater(() -> {
                            txtStudent.setText(finalStudentName);
                            this.repaint();
                        });

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (btnChooseStudent.getText().equals("随机学生")) {
                            break;
                        }
                    }
                }).start();
            }
        });
        // TODO 缺勤
        btnAbsence.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, "已记录缺勤", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // TODO 请假
        btnLeave.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, "已记录请假", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // TODO 答题
        btnAnswer.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, "回答正确", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
