package com.qq.client.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FriendModel {

    public JPanel jPanel;
    private JLabel jLabel;
    private JButton jButton;

    public FriendModel(String nickname){
        jLabel = new JLabel();
        jLabel.setBounds(new Rectangle(52, 10, 80, 20));
        jLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
        jLabel.setText(nickname);

        jButton = new JButton();
        jButton.setBounds(new Rectangle(8, 10, 40, 40));
        jButton.setBackground(new Color(236, 255, 236));
        jButton.setIcon(new ImageIcon("src/com/qq/img/9.png"));
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exchangeEnter();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exchangeExited();
            }
        });

        jPanel = new JPanel(null);
        jPanel.setSize(new Dimension(185, 60));
        jPanel.add(jButton, null);
        jPanel.add(jLabel, null);
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exchangeEnter();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exchangeExited();
            }
        });
    }

    public void exchangeEnter(){
        jPanel.setBackground(new Color(192, 224, 248));
    }

    public void exchangeExited(){
        jPanel.setBackground(null);
    }
}
