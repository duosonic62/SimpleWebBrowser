package com.example.WebApp;

import com.example.webServer.Handler;

/**
 * Webアプリケーションインターフェース
 * アプリケーションはこのインターフェースを継承し実装する。
 *
 * @author rikusekiguchi 2018/07/07
 */
public interface WebApp {

    /**
     * 各アプリケーション固有のハンドラーを生成するメソッド
     *
     */
    public abstract Handler getHandler();
}
