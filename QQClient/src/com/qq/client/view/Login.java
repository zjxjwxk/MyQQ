package com.qq.client.view;

import com.qq.client.controller.Client;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame{

    //定义北部需要的组件
    private JLabel jLabel;

    //定义中部需要的组件
    private JPanel jPanel;
    private JLabel jLabel2, jLabel3, jLabel4;
    private JTextField jtf;
    private JPasswordField jpf;
    private JCheckBox jcb;
    private JButton jButton;
    private User user;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            try
            {
                UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
                Login login = new Login();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public Login(){
        //北部
        jLabel = new JLabel(new ImageIcon("src/com/qq/img/1.png"));

        //中部
        jPanel = new JPanel(null);
        jLabel2 = new JLabel(new ImageIcon("src/com/qq/img/5.png"));
        jLabel3 = new JLabel("注册账号");
        jLabel4 = new JLabel("忘记密码");
        jtf = new JTextField();
        jpf = new JPasswordField();
        jcb = new JCheckBox("记住密码");
        jButton = new JButton(new ImageIcon("src/com/qq/img/2.png"));

        jLabel2.setBounds(35, 27, 79, 79);
        jLabel3.setBounds(330, 30, 50, 30);
        jLabel4.setBounds(330, 70, 50, 30);
        jtf.setBounds(125, 30, 194, 30);
        jpf.setBounds(125, 70, 194, 30);
        jcb.setBounds(125, 105, 194, 30);
        jcb.setFocusPainted(false);

        jButton.setBounds(125, 140, 194, 30);
        jButton.setPreferredSize(new Dimension(194, 30));
        jButton.setBorderPainted(false);
        jButton.setFocusPainted(false);

        jLabel3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new   Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton.setIcon(new ImageIcon("src/com/qq/img/3.png"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jButton.setIcon(new ImageIcon("src/com/qq/img/4.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jButton.setIcon(new ImageIcon("src/com/qq/img/2.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jButton.setIcon(new ImageIcon("src/com/qq/img/3.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton.setIcon(new ImageIcon("src/com/qq/img/2.png"));
            }
        });

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = jtf.getText();
                String password;

                if (jpf.getPassword().length == 0){
                    password = "null";
                }else {
                    password = String.valueOf(jpf.getPassword());
                }

                Client client = new Client();
                String message = client.login(number, password);
                if (message.equals("number can't be empty")){
                    JOptionPane.showMessageDialog(null, "请输入QQ号！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (message.equals("number doesn't exist")){
                    JOptionPane.showMessageDialog(null, "QQ号不存在！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if(message.equals("password can't be empty")){
                    JOptionPane.showMessageDialog(null, "请输入密码！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if(message.equals("password isn't correct")){
                    JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
                } else{
                    System.out.println(message);
                    user = client.getUser(number);
                    System.out.println(user.getNumber());
                    new FriendList(user);
                    dispose();
                }
            }
        });

        jPanel.add(jtf);
        jPanel.add(jpf);
        jPanel.add(jcb);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jLabel4);
        jPanel.add(jButton);

        this.add(jLabel, "North");
        this.add(jPanel, "Center");

        this.setResizable(false);
        this.setSize(430, 330);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public User getUser(){
        return this.user;
    }
}
