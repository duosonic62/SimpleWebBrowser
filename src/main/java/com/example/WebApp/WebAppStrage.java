package com.example.WebApp;

import com.example.WebApp.bulletinBoardApp.BulletinBoardApp;
import com.example.WebApp.staticServerApp.StaticWebApp;

import java.io.File;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Webアプリケーションを保持しておくクラス。
 * URLとアプリケーションをマッピングする。
 *
 * @author rikusekiguchi 2018/07/07
 */
public class WebAppStrage {
    /**
     * パスとwebアプリを保持するMap
     */
    private static Map<String, WebApp> webAppMap = new HashMap<>();

    /**
     * アプリケーションの初期化。
     * 各アプリケーションとパスを紐付けする。
     */
    public static void initApp() {
        StaticWebApp staticWebApp           = new StaticWebApp();
        BulletinBoardApp bulletinBoardApp   = new BulletinBoardApp();

        // 静的なファイル(resources内)の読込
        File staticDir      = new File("src/main/resources");
        File[] staticFiles  = staticDir.listFiles();
        Arrays.stream(staticFiles)
                .forEach(file -> webAppMap.put("/"+file.getName(), staticWebApp));

        // 掲示板アプリの読込
        webAppMap.put("/BulletinBoard", bulletinBoardApp);

    }

    /**
     * アプリケーション取得。
     * pathに対応するAppがなければnullを返す。
     *
     * @param path 取得したいAppへのパス
     * @return WebApp
     */
    public static WebApp getWebApp(String path){
        System.out.println("Key : " + path);
        if(!webAppMap.containsKey(path)){
            return null;
        }else{
            return webAppMap.get(path);
        }

    }
}
