package com.qq.client.model;
/*
自定义单元渲染器
 */
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings({ "rawtypes", "serial" })
public class FriendListCellRenderer  extends JLabel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        User user = (User)value; //把数据转换为user对象，在AbstractListModel中传过来的是一个user对象；
        /*******设置JLabel的文字******/
        String text = "<html>" + user.getNickname() + "<br/>"+ "       " + " <html/>";
        setText(text); //设置JLable的文字
        setFont(new Font("微软雅黑", 0, 16));
        /*******设置JLabel的图片*****/
        setIcon(new ImageIcon("src/com/qq/img/9.png"));//设置JLable的图片
        setIconTextGap(10);//设置JLable的图片与文字之间的距离
        return this;
    }
}