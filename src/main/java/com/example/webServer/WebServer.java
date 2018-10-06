package com.example.webServer;


import com.example.WebApp.WebAppStrage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * クライアントの通信を受け付け、リクエストの処理を呼び出すクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class WebServer {

    private static final int PORT = 8080;

    /**
     * メインメソッド
     * @param args コマンドライン引数
     */
    public static void main(String[] args){
        WebServer webServer = new WebServer();
        webServer.init();
    }

    /**
     * クライアントの通信を受け付け、リクエストの処理を呼び出すメソッド
     */
    private void init(){
        //ソケットの用意
        ServerSocket serverSocket = null;

        // アプリケーションの初期化
        WebAppStrage.initApp();

        try {
            serverSocket = new ServerSocket(PORT);
            //ソケットの生成->リクエスト待ち->スレッドの呼出
            while(true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
