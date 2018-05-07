package com.qq.client.model;

import com.qq.client.view.Chat;

import java.io.*;
import java.net.Socket;

public class WaitMessageThread extends Thread {
    
    Socket socket = null;
    Chat chat;

    public WaitMessageThread(Chat chat){
        this.chat = chat;
    }

    public void run(){

        while (true){
            try {

                socket = new Socket("localhost", 8888);

                socket.setSoTimeout(999999999);

                System.out.println("WaiMessageThread start");

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("waitForMessage");
                pw.write("\n");
                pw.write(chat.getUser().getNumber());
                pw.flush();
                socket.shutdownOutput();

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String sendTime = br.readLine();
                String senderName = br.readLine();
                String senderNumber = br.readLine();
                String message = br.readLine();
                chat.appendTextArea(senderName + "(" + senderNumber + ")" + "  " + sendTime + "\n   " + message + "\n");
                socket.shutdownInput();

                br.close();
                isr.close();
                is.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
