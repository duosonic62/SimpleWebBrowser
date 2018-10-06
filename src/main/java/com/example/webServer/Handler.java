package com.example.webServer;




import java.io.File;
import java.io.IOException;


/**
 * 実処理(doGet, doPost)を行う継承元クラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class Handler {

    /**
     * GETが来た場合の処理
     * オーバーライドされなければで共通エラーページに飛ばす
     *
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void doGet(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException{
        File responseFile = new File("src/main/resources/404.html");
        httpResponse.setResponseFile(responseFile);
        httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, HTTPResponse.SD_NOT_FOUND);
    }

    /**
     * POSTが来た場合の処理
     * オーバーライドされなければで共通エラーページに飛ばす
     *
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void doPOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException{
        File responseFile = new File("src/main/resources/404.html");
        httpResponse.setResponseFile(responseFile);
        httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, HTTPResponse.SD_NOT_FOUND);
    }


}
