package com.qq.client.controller;

import com.qq.common.User;

import java.io.*;
import java.net.Socket;

public class Client {

    public String login(String number, String password){

        String message = null;

        try{
            //创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("localhost", 8888);

            //获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream(); //字节输出流
            PrintWriter pw = new PrintWriter(os); //将输出流包装为打印流
            pw.write("login\n");
            pw.write(number);
            pw.write("\n");
            pw.write(password);
            pw.flush();
            socket.shutdownOutput(); //关闭输出流

            //获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            message = br.readLine();

            //关闭资源
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return message;
    }

    public User getUser(String number){

        User user = null;

        try{
            Socket socket = new Socket("localhost", 8888);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("getUser");
            pw.write("\n");
            pw.write(number);
            pw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            try {
                System.out.println("readObject begin");
                user = (User) ois.readObject();
                System.out.println(user.getNickname());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            socket.shutdownInput();

            ois.close();
            is.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return user;
    }

    public String signUp(String nickname, String number, String password, String confirmPassword){

        String message = null;

        try{
            //创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("localhost", 8888);

            //获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream(); //字节输出流
            PrintWriter pw = new PrintWriter(os); //将输出流包装为打印流
            pw.write("sign up\n");
            pw.write(nickname);
            pw.write("\n");
            pw.write(number);
            pw.write("\n");
            pw.write(password);
            pw.write("\n");
            pw.write(confirmPassword);
            pw.flush();
            socket.shutdownOutput(); //关闭输出流

            //获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            message = br.readLine();
            socket.shutdownInput();

            //关闭资源
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return message;
    }

    public void sendMessage(String senderNumber, String senderName, String getterNumber, String sendTime, String message){

        try{
            Socket socket = new Socket("localhost", 8888);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("sendMessage\n");
            pw.write(senderNumber);
            pw.write("\n");
            pw.write(senderName);
            pw.write("\n");
            pw.write(getterNumber);
            pw.write("\n");
            pw.write(sendTime);
            pw.write("\n");
            pw.write(message);
            pw.flush();
            socket.shutdownOutput();

            pw.close();
            os.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
