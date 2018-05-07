package com.qq.server.model;

import com.qq.server.action.UserAction;
import com.qq.common.User;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;
    private OutputStream os = null;
    private PrintWriter pw = null;
    private ObjectOutputStream oos = null;

    private String number;

    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try{
            is = socket.getInputStream(); //字节输入流
            isr = new InputStreamReader(is); //将字节流转换为字符流
            br = new BufferedReader(isr); //为输入流添加缓冲
            String type = br.readLine();
            if (type.equals("login")){
                System.out.println("******login begin******");
                login();
            } else if (type.equals("sign up")){
                System.out.println("******signUp begin******");
                signUp();
            } else if (type.equals("getUser")){
                System.out.println("******getUser begin******");
                getUser();
            } else if (type.equals("sendMessage")){
                System.out.println("******sendMessage begin******");
                sendMessage();
            } else if (type.equals("waitForMessage")){
                System.out.println("******waitForMessage begin******");
                waitForMessage();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
//        finally {
//            //关闭资源
//            try {
//                if (br != null)
//                    br.close();
//                if (isr != null)
//                    isr.close();
//                if (is != null)
//                    is.close();
//                if (socket != null)
//                    socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void login(){



        try{
            //获取账号密码的信息输入流，并读取客户端信息
            String number = br.readLine();
            this.number = number;
            String password = br.readLine();
            System.out.println("number:" + number);
            System.out.println("password:" + password);
            String message = null;
            UserAction userAction = new UserAction();
            if (number.equals("")){
                message = "number can't be empty";
            }else if (! userAction.ifExists(number)){
                message = "number doesn't exist";
            }else if (password.equals("null")){
                message = "password can't be empty";
            }else if (! userAction.ifCorrect(number, password)){
                message = "password isn't correct";
            }else{
                message = "login successfully";
            }
            socket.shutdownInput();

            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();

            pw = new PrintWriter(os); //包装为打印流
            System.out.println(message);
            pw.write(message);
            pw.flush(); //调用flush()将缓冲输出

            socket.shutdownOutput();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (oos != null)
                    oos.close();
                if (pw != null)
                    pw.close();
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getUser(){

        try{
            String number = br.readLine();
            os = socket.getOutputStream();
            UserAction userAction = new UserAction();
            User user = userAction.get(number);
            user.setFriends(userAction.getAllUsers());
            System.out.println(user.getNickname());
            System.out.println(user.getNumber());
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);
            oos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void signUp(){
        try{

            String nickname = br.readLine();
            String number = br.readLine();
            String password = br.readLine();
            String confirmPassword = br.readLine();
            System.out.println("nickname:" + nickname);
            System.out.println("number:" + number);
            System.out.println("password:" + password);
            System.out.println("confirmPassword:" + confirmPassword);
            UserAction userAction = new UserAction();
            String message = null;
            if (nickname.equals("")){
                message = "nickname can't be empty";
            }else if (number.equals("")){
                message = "number can't be empty";
            }else if (userAction.ifExists(number)){
                message = "number already exists";
            }else if (password.equals("null")){
                message = "password can't be empty";
            }else if (confirmPassword.equals("null")){
                message = "confirmPassword can't be empty";
            }else if (! password.equals(confirmPassword)){
                message = "confirmPassword isn't equal to password";
            }
            else{
                message = "sign up successfully";
                User user = new User(nickname, number, password);
                userAction.add(user);
            }
            socket.shutdownInput();

            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os); //包装为打印流
            System.out.println(message);
            pw.write(message);
            pw.flush(); //调用flush()将缓冲输出

            socket.shutdownOutput();

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //关闭资源
            try {
                if (pw != null)
                    pw.close();
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(){

        try{

            String senderNumber = br.readLine();
            String senderName = br.readLine();
            String getterNumber = br.readLine();
            String sendTime = br.readLine();
            String message = br.readLine();
            System.out.println("sendTime:" + sendTime);
            System.out.println("sender:" + senderName);
            System.out.println("getter:" + getterNumber);
            System.out.println("message:" + message);
            socket.shutdownInput();

            Socket socket = ManageClientSocket.getSocket(getterNumber);
            System.out.println(socket.getInetAddress());
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write(sendTime);
            pw.write("\n");
            pw.write(senderName);
            pw.write("\n");
            pw.write(senderNumber);
            pw.write("\n");
            pw.write(message);
            pw.flush();
            socket.shutdownOutput();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void waitForMessage(){
        try {
            String number = br.readLine();
            System.out.println(number);
            ManageClientSocket.addClientSocket(number, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
