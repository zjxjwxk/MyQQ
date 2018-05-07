package com.qq.server.controller;

import com.qq.server.model.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try{
            //创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);

            Socket socket = null;

            System.out.println("***服务器启动,等待客户端的连接***");

            //循环监听等待客户端的连接
            while (true){
                //调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //启动线程
                serverThread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
