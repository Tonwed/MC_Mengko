package com.tonwed.mengko;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.bukkit.Bukkit.broadcastMessage;
import static org.bukkit.Bukkit.getLogger;


public class TCP {
    public static int port = -1;
    public static boolean initialize = false;
    public static BufferedWriter writer;
    public static void Start(int tcpPort){
        TCP.port = tcpPort;
        Thread t1 = new Start();
        t1.start(); // 启动新线程

    }
    public static void SendMsg(String msg){
        try {
            if (TCP.initialize){
                TCP.writer.write(msg);
                TCP.writer.flush();
            }
            getLogger().info("未完成TCP连接初始化！");
        }catch (Exception e){

        }

    }
}

class Start extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(TCP.port); // 监听指定端口
            getLogger().info("[Mengko] Server is running...");
            for (;;) {
                Socket sock = ss.accept();
                getLogger().info("connected from " + sock.getRemoteSocketAddress());
                Thread t = new Handler(sock);
                t.start();
            }
        }catch (Exception e){

        }
        System.out.println("start new thread!");
    }
}

class Handler extends Thread {
    Socket sock;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
            }
            getLogger().info("client disconnected.");
        }
    }

    public void handle(InputStream input, OutputStream output) throws IOException {
        TCP.writer  = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        TCP.initialize = true;
        TCP.writer.write("Connected");
        TCP.writer.flush();
        for (;;) {
            String s = reader.readLine();
            getLogger().info(s);
            broadcastMessage("[Mengko] " + s);
            if (s.equals("bye")) {
                TCP.writer.write("bye\n");
                TCP.writer.flush();
                break;
            }
            TCP.writer.write("ok: " + s);
            TCP.writer.flush();
        }
    }
}