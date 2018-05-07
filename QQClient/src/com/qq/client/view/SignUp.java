package com.qq.client.view;

import com.qq.client.controller.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame{

    private JPanel jPanel, jPanel2;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JTextField jtf, jtf2;
    private JPasswordField jpf, jpf2;
    private JButton jButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            try
            {
                UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
                SignUp signUp = new SignUp();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public SignUp(){

        jPanel = new JPanel();
        jPanel2 = new JPanel(null);

        jLabel1 = new JLabel("注册账号");
        jLabel2 = new JLabel("昵称");
        jLabel3 = new JLabel("QQ号码");
        jLabel4 = new JLabel("密码");
        jLabel5 = new JLabel("确认密码");
        jtf = new JTextField();
        jtf2 = new JTextField();
        jpf = new JPasswordField();
        jpf2 = new JPasswordField();
        jButton = new JButton("注册");

        jLabel1.setFont(new Font("微软雅黑", 1, 24));
        jLabel2.setFont(new Font("微软雅黑", 0, 16));
        jLabel3.setFont(new Font("微软雅黑", 0, 16));
        jLabel4.setFont(new Font("微软雅黑", 0, 16));
        jLabel5.setFont(new Font("微软雅黑", 0, 16));

        jLabel2.setBounds(42, 22, 100, 20);
        jLabel3.setBounds(16, 58, 100, 20);
        jLabel4.setBounds(42, 92, 100, 20);
        jLabel5.setBounds(10, 128, 100, 20);
        jtf.setBounds(88, 21, 194, 30);
        jtf2.setBounds(88, 52, 194, 30);
        jpf.setBounds(88, 87, 194, 30);
        jpf2.setBounds(88, 122, 194, 30);
        jButton.setBounds(108, 162, 80, 30);
        jButton.setFocusPainted(false);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = jtf.getText();
                String number = jtf2.getText();
                String password;
                String confirmPassword;

                if (jpf.getPassword().length == 0){
                    password = "null";
                }else {
                    password = String.valueOf(jpf.getPassword());
                }

                if (jpf2.getPassword().length == 0){
                    confirmPassword = "null";
                }else {
                    confirmPassword = String.valueOf(jpf2.getPassword());
                }

                System.out.println("nickname:" + nickname);
                System.out.println("number:" + number);
                System.out.println("password:" + password);
                System.out.println("confirmPassword:" + confirmPassword);

                Client client = new Client();
                String message = client.signUp(nickname, number, password, confirmPassword);
                if (message.equals("nickname can't be empty")){
                    JOptionPane.showMessageDialog(null, "昵称不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (message.equals("number can't be empty")){
                    JOptionPane.showMessageDialog(null, "QQ号不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (message.equals("number already exists")){
                    JOptionPane.showMessageDialog(null, "QQ号已存在！", "提示", JOptionPane.ERROR_MESSAGE);
                }else if (message.equals("password can't be empty")){
                    JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (message.equals("confirmPassword can't be empty")){
                    JOptionPane.showMessageDialog(null, "确认密码不能为空!", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (message.equals("confirmPassword isn't equal to password")){
                    JOptionPane.showMessageDialog(null, "确认密码不一致！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showConfirmDialog(null, "注册成功！", "提示", JOptionPane.DEFAULT_OPTION);
                    dispose();
                }
            }
        });

        jPanel.add(jLabel1);
        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel4);
        jPanel2.add(jLabel5);
        jPanel2.add(jtf);
        jPanel2.add(jtf2);
        jPanel2.add(jpf);
        jPanel2.add(jpf2);
        jPanel2.add(jButton);

        this.add(jPanel, "North");
        this.add(jPanel2, "Center");

        this.setResizable(false);
        this.setSize(300, 280);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
