package com.qq.client.view;

import com.qq.client.controller.Client;
import com.qq.client.model.WaitMessageThread;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends JFrame {

    private JPanel jPanel, jPanel2;
    private JLabel jLabel;
    private JTextArea jta, jta2;
    private JScrollPane jsp;
    private JButton jButton;
    private User user;

    public Chat(User user, User friend){

        WaitMessageThread waitMessageThread = new WaitMessageThread(this);
        waitMessageThread.start();

        this.user = user;

        jPanel = new JPanel(null);
        jPanel2 = new JPanel(null);
        jLabel = new JLabel(friend.getNickname(), new ImageIcon("src/com/qq/img/9.png"), JLabel.LEFT);
        jta = new JTextArea();
        jta2 = new JTextArea();
        jButton = new JButton("发送");

        jPanel.setBackground(new Color(0, 155, 219));
        jPanel.setBounds(0, 0, 750, 60);
        jLabel.setFont(new Font("微软雅黑", 0, 16));
        jLabel.setBounds(5, 5, 200, 50);
        jPanel.add(jLabel);

        jta.setBounds(8, 8, 726, 105);
        jta.setFont(new Font("微软雅黑", 0, 16));
        jta.setLineWrap(true);
        jta2.setBounds(0, 0, 300, 300);
        jta2.setFont(new Font("微软雅黑", 0, 16));
        jta2.setLineWrap(true);
        jta2.setEditable(false);
        jta2.setBorder(null);
        jta2.setOpaque(false);

        jsp = new JScrollPane(jta2);
        jsp.setBorder(null);
        jsp.setBounds(5, 62, 730, 380);
        jButton.setBounds(675, 120, 60, 30);
        jButton.setFocusPainted(false);

        jPanel2.setBackground(new Color(0, 155, 219));
        jPanel2.setBounds(0, 450, 750, 160);
        jPanel2.add(jta);
        jPanel2.add(jButton);

        this.add(jsp);
        this.setLayout(null);
        this.add(jPanel);
        this.add(jPanel2);

        this.setSize(750, 640);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                String senderNumber = user.getNumber();
                String senderName = user.getNickname();
                String getterNumber = friend.getNumber();
                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                String sendTime = sdf.format(new Date());
                String message = jta.getText();
                client.sendMessage(senderNumber, senderName, getterNumber, sendTime, message);
                System.out.println("sendTime:" + sendTime);
                System.out.println("sender:" + senderName);
                System.out.println("getter:" + getterNumber);
                System.out.println("message:" + message);
                appendTextArea(senderName + "(" + senderNumber + ")" + "  " + sendTime + "\n   " + message + "\n");
                jta.setText("");
                jta.requestFocus();
            }
        });
    }

    public void appendTextArea(String message){
        jta2.append(message);
    }

    public User getUser() {
        return user;
    }
}
