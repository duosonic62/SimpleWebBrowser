package com.example.WebApp.bulletinBoardApp;

import com.example.WebApp.WebApp;
import com.example.webServer.Handler;


/**
 * 動的なファイルを返すWebアプリケーション
 *
 * @author rikusekiguchi 2018/07/07
 */
public class BulletinBoardApp implements WebApp {

    /**
     * このAppの固有のハンドラ
     */
    private BulletinBoardAppHandler bulletionBoardAppHandler;

    /**
     * コンストラクタ
     */
    public BulletinBoardApp(){
        bulletionBoardAppHandler = new BulletinBoardAppHandler();
    }

    /**
     * 固有ハンドラを設定する
     *
     * @return 固有ハンドラ
     */
    @Override
    public Handler getHandler() {
        return bulletionBoardAppHandler;
    }
}
