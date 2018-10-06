package com.example.WebApp.staticServerApp;

import com.example.WebApp.WebApp;
import com.example.webServer.Handler;


/**
 * 静的なファイルを返すWebアプリケーション
 *
 * @author rikusekiguchi 2018/07/07
 */
public class StaticWebApp implements WebApp {

    /**
     * このAppの固有のハンドラ
     */
    StaticWebAppHandler staticWebAppHandler;

    /**
     * コンストラクタ
     */
    public StaticWebApp(){
        staticWebAppHandler = new StaticWebAppHandler();
    }


    /**
     * 固有ハンドラを設定する
     *
     * @return 固有ハンドラ
     */
    @Override
    public Handler getHandler() {
        return staticWebAppHandler;
    }
}
