package com.qq.server.model;

import java.net.Socket;
import java.util.HashMap;

public class ManageClientSocket {

    public static HashMap<String, Socket> sockets = new HashMap<>();

    //向hashMap中添加客户端通信线程
    public static void addClientSocket(String number, Socket socket){
        System.out.println(socket.getInetAddress());
        sockets.put(number, socket);
        System.out.println(sockets.size());
    }

    public static Socket getSocket(String number){
        return sockets.get(number);
    }
}
