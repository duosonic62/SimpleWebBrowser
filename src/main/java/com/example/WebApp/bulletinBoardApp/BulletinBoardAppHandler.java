package com.example.WebApp.bulletinBoardApp;

import com.example.WebApp.bulletinBoardApp.dao.BulletinBoardDAO;
import com.example.WebApp.bulletinBoardApp.dao.ItemDAO;
import com.example.WebApp.bulletinBoardApp.dao.UserDAO;
import com.example.WebApp.bulletinBoardApp.entity.ItemEntity;
import com.example.WebApp.bulletinBoardApp.entity.UserEntity;
import com.example.webServer.HTTPRequest;
import com.example.webServer.HTTPResponse;
import com.example.webServer.Handler;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class BulletinBoardAppHandler extends Handler {
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private BulletinBoardDAO bulletinBoardDAO;
    private BulletinBoardWriter bulletinBoardWriter;

    public BulletinBoardAppHandler(){
        try {
            userDAO = new UserDAO();
            itemDAO = new ItemDAO();
            bulletinBoardDAO = new BulletinBoardDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bulletinBoardWriter = new BulletinBoardWriter();
    }



    /**
     * GETが来た場合の処理
     * 動的にリクエストを処理する
     *
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void doGet(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        // 表示する記事を取得
        try {
            List<String> bulletinBoardHtml = bulletinBoardWriter.createHtml(bulletinBoardDAO.getAllItem());
            httpResponse.sendResponse(HTTPResponse.SC_OK, HTTPResponse.SD_OK, bulletinBoardHtml);
        } catch (Exception e) {
            File responseFile = new File("src/main/resources/500.html");
            httpResponse.setResponseFile(responseFile);
            httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, HTTPResponse.SD_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    /**
     * POSTが来た場合の処理
     * 記事を登録する。
     *
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void doPOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        System.out.println("ok");
        // パラメータを取得
        String name     = httpRequest.getRequestParam("name");
        String title    = httpRequest.getRequestParam("title");
        String content  = httpRequest.getRequestParam("content");

        System.out.println(name+title+content);

        try {
            // 以前に使われていたユーザ名か調べる
            UserEntity user = userDAO.findUser(name);

            //使われていなければ、新規登録
            if (user == null){
                userDAO.createUser(name);
                user = userDAO.findUser(name);
            }

            // 登録する記事エンティティを作成
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setTitle(title);
            itemEntity.setContent(content);
            itemEntity.setUserId(user.getUserId());

            // 記事を登録する
            // 登録できなければエクセプションをはかせる。
            if (itemDAO.createItem(itemEntity) == 0){
                throw new Exception();
            }


        } catch (Exception e) {
            File responseFile = new File("src/main/resources/500.html");
            httpResponse.setResponseFile(responseFile);
            httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, HTTPResponse.SD_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        // 記事を表示する
        doGet(httpRequest, httpResponse);
    }
}
