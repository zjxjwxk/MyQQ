package com.qq.client.view;

import com.qq.client.model.FriendModel;

import javax.swing.*;

public class FriendPane extends JPanel {

    private JLabel jLabel, jLabel1, jLabel2, jLabel3, jLabel4;

    public FriendPane(){

        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon("src/com/qq/img/9.png"));
        jLabel1.add(new FriendModel("Wxk1").jPanel);
        jLabel1.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabel2 = new JLabel();
        jLabel2.setIcon(new ImageIcon("src/com/qq/img/9.png"));
        jLabel2.add(new FriendModel("Wxk2").jPanel);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabel3 = new JLabel();
        jLabel3.setIcon(new ImageIcon("src/com/qq/img/9.png"));
        jLabel3.add(new FriendModel("Wxk3").jPanel);
        jLabel3.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabel4 = new JLabel();
        jLabel4.setIcon(new ImageIcon("src/com/qq/img/9.png"));
        jLabel4.add(new FriendModel("Wxk4").jPanel);
        jLabel4.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabel = new JLabel(new ImageIcon("src/com/qq/img/6.png"));

    }
}
