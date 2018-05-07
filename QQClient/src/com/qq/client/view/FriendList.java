package com.qq.client.view;

import com.qq.client.controller.Client;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FriendList extends JFrame{

    private JPanel jPanel1, jPanel2, jPanel3;
    private JLabel jLabel, jLabel2, jLabel3;
    private JButton jButton;
    private JList<User> jList;
    private JScrollPane jsp;
    private JPopupMenu menu;
    private User user;
    private boolean ifUnfold = false;

    public FriendList(User user) {

        this.user = user;

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel(null);

        jLabel = new JLabel(new ImageIcon("src/com/qq/img/5.png"));
        jLabel2 = new JLabel(user.getNickname());
        jLabel3 = new JLabel();
        jButton = new JButton(new ImageIcon("src/com/qq/img/6.png"));
        jList = new JList<>();

        jButton.setPreferredSize(new Dimension(362, 29));
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);

        jPanel1.setBackground(new Color(0, 155, 219));
        jLabel2.setFont(new Font("微软雅黑", 0, 24));

        ArrayList<User> users = (ArrayList<User>) user.getFriends();

        jPanel3.setPreferredSize(new Dimension(200, users.size() * 51));

        for (int i = 0; i < users.size(); i ++){
            jLabel3 = new JLabel(users.get(i).getNickname(), new ImageIcon("src/com/qq/img/9.png"), JLabel.LEFT);
            jLabel3.setBounds(0, i * 51, 200, 51);
            jLabel3.setFont(new Font("微软雅黑", 0, 16));
            String friendName = jLabel3.getText();
            int index = i;
            jLabel3.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2)
                        new Chat(user, users.get(index));
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            jPanel3.add(jLabel3);
        }

        jsp = new JScrollPane(jPanel3);

        jsp.setPreferredSize(new Dimension(350, 630));
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setBorder(null);

        this.RightMouse();
        jsp.add(menu);

//        ArrayList<User> users = (ArrayList<User>) user.getFriends();

//        //创建一个JList,显示好友类表
//        FriendListModel buddy = new FriendListModel(users);
//        @SuppressWarnings("rawtypes")
//        JList buddyList = new JList(buddy);
//        //设置单元渲染器
//        buddyList.setCellRenderer(new FriendListCellRenderer());
//        buddyList.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
//        buddyList.setPreferredSize(new Dimension(300, 1000));
//        //给好友列表添加滚动条
//        jsp = new JScrollPane(buddyList);
//        jsp.setPreferredSize(new Dimension(360, 640));
//        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jPanel2.add(jButton);
        jPanel2.add(jsp);

        jPanel1.add(jLabel);
        jPanel1.add(jLabel2);

        this.add(jPanel1, "North");
        this.add(jPanel2, "Center");

        this.setResizable(false);
        this.setSize(362, 800);

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        this.setLocation((int)(screenWidth * 0.7), (int)(screenHeight * 0.1));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        jButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (! ifUnfold){
                    jButton.setIcon(new ImageIcon("src/com/qq/img/8.png"));
                    jsp.setVisible(true);
                    ifUnfold = true;
                }else {
                    jButton.setIcon(new ImageIcon("src/com/qq/img/7.png"));
                    jsp.setVisible(false);
                    ifUnfold = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (! ifUnfold){
                    jButton.setIcon(new ImageIcon("src/com/qq/img/7.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (! ifUnfold){
                    jButton.setIcon(new ImageIcon("src/com/qq/img/6.png"));
                }else {
                    jButton.setIcon(new ImageIcon("src/com/qq/img/8.png"));
                }
            }
        });


    }
    public void RightMouse(){
        JMenuItem refresh;
        menu = new JPopupMenu();
        refresh = new JMenuItem("刷新好友列表");
        menu.add(refresh);
        jPanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    menu.show(FriendList.this, e.getX() + 5, e.getY() + 155);
                }
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                new FriendList(client.getUser(user.getNumber()));
                dispose();
            }
        });
    }
}
