package com.student.view;

import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        btnAbsence.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String filePath = Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt";
                String studentName = txtStudent.getText(); // 要查找的学生名字
                int decrementValue = 5; // 要减去的值

                try {
                    // 读取文件
                    List<String> lines = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();

                    // 查找并修改指定行
                    for (int i = 0; i < lines.size(); i++) {
                        String currentLine = lines.get(i);
                        if (currentLine.startsWith(studentName + ",")) {
                            String[] parts = currentLine.split(",");
                            if (parts.length > 3) {
                                int score = Integer.parseInt(parts[3]);
                                score -= decrementValue; // 修改值
                                parts[3] = String.valueOf(score); // 更新分数
                                lines.set(i, String.join(",", parts)); // 重新组装行
                            }
                            break; // 找到后退出循环
                        }
                    }

                    // 将修改后的内容写回文件
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    writer.close();

                    System.out.println("文件更新成功！");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "已记录缺勤", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnLeave.addActionListener(e -> {
            if (txtStudent.getText() == null || txtStudent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机选择学生", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String filePath = Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt"; // 替换为你的文件路径
                String studentName = txtStudent.getText(); // 要查找的学生名字
                int decrementValue = 2; // 要减去的值

                try {
                    // 读取文件
                    List<String> lines = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();

                    // 查找并修改指定行
                    for (int i = 0; i < lines.size(); i++) {
                        String currentLine = lines.get(i);
                        if (currentLine.startsWith(studentName + ",")) {
                            String[] parts = currentLine.split(",");
                            if (parts.length > 3) {
                                int score = Integer.parseInt(parts[3]);
                                score -= decrementValue; // 修改值
                                parts[3] = String.valueOf(score); // 更新分数
                                lines.set(i, String.join(",", parts)); // 重新组装行
                            }
                            break; // 找到后退出循环
                        }
                    }

                    // 将修改后的内容写回文件
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    writer.close();

                    System.out.println("文件更新成功！");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
