package com.qq.client.model;

/*
自定义ListModel列表模型
 */
import com.qq.common.User;

import javax.swing.*;
import java.util.ArrayList;

public class FriendListModel extends AbstractListModel {

    ArrayList<User> uArray; //好友类表

    public FriendListModel(ArrayList<User> uArray){
        this.uArray = uArray;
    }

    @Override
    public int getSize() {
        return uArray.size();
    }

    @Override
    public Object getElementAt(int index) {
        return uArray.get(index);
    }
}
