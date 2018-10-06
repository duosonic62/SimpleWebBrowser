package com.example.webServer;

import com.example.WebApp.WebApp;
import com.example.WebApp.WebAppStrage;

import java.io.IOException;
import java.net.Socket;


/**
 * リクエストごとにサーバの起動を行うクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class ServerThread extends Thread {
    Socket socket;

    /**
     * コンストラクタ
     *
     * @param socket ソケット
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    /**
     * リクエスト毎にサーバを立ち上げる
     */
    public void run() {
        try {
            HTTPRequest httpRequest = new HTTPRequest(socket.getInputStream());
            HTTPResponse httpResponse = new HTTPResponse(socket.getOutputStream());

            // WebAppの呼出
            WebApp webApp = WebAppStrage.getWebApp(httpRequest.getUrl());
            Handler handler;

            // 固有ハンドラの呼出
            if (webApp != null) {
                handler = webApp.getHandler();
            } else {
                // 対応するハンドラがなければデフォルトハンドラを使う(NotFoundになる)
                handler = new Handler();
            }

            // GetとPostに振り分け
            if (httpRequest.getRequestMethod().equals("GET")) {
                handler.doGet(httpRequest, httpResponse);
            } else {
                handler.doPOST(httpRequest, httpResponse);
            }

        } catch (IOException e) {
            // エラーのハンドリング
            e.printStackTrace();
        } finally {
            try {
                if (socket!=null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
