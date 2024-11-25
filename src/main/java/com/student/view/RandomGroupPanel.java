package com.student.view;

import com.student.util.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RandomGroupPanel extends JPanel {
    private JLabel lbl1 = new JLabel("小组名：");
    private JLabel lbl2 = new JLabel("学生姓名：");
    private JLabel lbl3 = new JLabel("学生照片：");
    private JLabel lblPic = new JLabel("照片");
    private JLabel lbl4 = new JLabel("小组评分");
    private JTextField txtGroup = new JTextField();
    private JTextField txtStudent = new JTextField();
    private JTextField txtScore = new JTextField();
    private JButton btnChooseGroup = new JButton("随机小组");
    private JButton btnChooseStudent = new JButton("随机学生");
    private JButton btnAbsence = new JButton("缺勤");
    private JButton btnLeave = new JButton("请假");
    private JButton btnScore = new JButton("小组评分");
    Thread threadGroup = null;   // 随机抽取小组的线程
    Thread threadStudent = null;   // 随机抽取小组的线程

    public RandomGroupPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "随机小组点名"));
        this.setLayout(null);
        this.add(lbl1);
        this.add(lbl2);
        this.add(lbl3);
        this.add(txtGroup);
        this.add(txtStudent);
        this.add(lblPic);
        this.add(btnChooseGroup);
        this.add(btnChooseStudent);
        this.add(btnAbsence);
        this.add(btnLeave);
        this.add(lbl4);
        this.add(txtScore);
        this.add(btnScore);

        lbl1.setBounds(50, 50, 100, 30);
        txtGroup.setBounds(50, 90, 100, 30);
        txtGroup.setEditable(false);
        btnChooseGroup.setBounds(50, 130, 100, 30);

        lbl4.setBounds(50, 190, 100, 30);
        txtScore.setBounds(50, 230, 100, 30);
        btnScore.setBounds(50, 270, 100, 30);

        lbl2.setBounds(220, 50, 100, 30);
        txtStudent.setBounds(220, 90, 130, 30);
        txtStudent.setEditable(false);
        lblPic.setBounds(220, 130, 130, 150);
        btnChooseStudent.setBounds(220, 300, 100, 30);
        btnAbsence.setBounds(220, 340, 60, 30);
        btnLeave.setBounds(290, 340, 60, 30);
        // 添加按钮功能
        // 随机小组
        btnChooseGroup.addActionListener(e -> {
            if (e.getActionCommand().equals("停")) {
                btnChooseGroup.setText("随机小组");
            } else {
                btnChooseGroup.setText("停");
                File file = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH);
                File[] files = file.listFiles();

                // 创建一个新的线程来执行随机选择
                new Thread(() -> {
                    while (true) {
                        int index = (int) (Math.random() * files.length);
                        while (files[index].getName().equals("students.txt")) {
                            index = (int) (Math.random() * files.length);
                        }
                        // 在事件调度线程中更新UI组件
                        int finalIndex = index;
                        SwingUtilities.invokeLater(() -> {
                            txtGroup.setText(files[finalIndex].getName().substring(0, files[finalIndex].getName().length() - 4));
                            this.repaint();
                        });

                        // 停顿0.1秒
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        // 检查按钮的文字状态
                        if (btnChooseGroup.getText().equals("随机小组")) {
                            break;
                        }
                    }
                }).start(); // 启动新线程
            }
        });

        // 随机学生
        btnChooseStudent.addActionListener(e -> {
            if (txtGroup.getText() == null || txtGroup.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先随机抽取小组", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (e.getActionCommand().equals("停")) {
                    btnChooseStudent.setText("随机学生");

                } else {
                    btnChooseStudent.setText("停");
                    File file = new File(Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/" + txtGroup.getText() + ".txt");
                    int count = 0;
                    // 使用BufferedReader读取文件
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader(file));
                        while (br.readLine() != null) {
                            count++;
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    // 创建一个新的线程来执行随机选择
                    int finalCount = count;
                    BufferedReader finalBr = br;

                    BufferedReader finalBr1 = br;

                    try {
                        br.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    new Thread(() -> {
                        while (true) {
                            BufferedReader br1 = null;
                            try {
                                br1 = new BufferedReader(new FileReader(file));
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            int index = (int) (Math.random() * finalCount);
                            String line = null;
                            // 在事件调度线程中更新UI组件
                            while (true) {
                                try {
                                    if ((line = br1.readLine()) == null) {
                                        break;
                                    }
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                index--;
                                if (index < 0) {
                                    break;
                                }
                            }
                            String[] strings = null;
                            if (line != null) {
                                strings = line.split(",");
                            }

                            if (strings != null) {
                                line = strings[0];
                            }

                            String finalLine = line;
                            SwingUtilities.invokeLater(() -> {
                                txtStudent.setText(finalLine);
                                this.repaint();
                            });

                            // 停顿0.1秒
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }

                            // 检查按钮的文字状态
                            if (btnChooseStudent.getText().equals("随机学生")) {
                                break;
                            }
                        }
                    }).start(); // 启动新线程
                }
            }
        });
        // TODO 缺勤
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
        // TODO 请假
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
        // TODO 给小组打分
        btnScore.addActionListener(e -> {
            if (txtGroup.getText() == null || txtGroup.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先抽取小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtScore.getText() == null || txtScore.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写分数", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int score1 = Integer.parseInt(txtScore.getText());
            String filePath = Constant.FILE_PATH + "/" + Constant.CLASS_PATH + "/students.txt"; // 替换为你的文件路径
            String studentName = txtStudent.getText(); // 要查找的学生名字
            int decrementValue = -score1; // 要减去的值

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
            JOptionPane.showMessageDialog(this, "已给小组打分", "", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
