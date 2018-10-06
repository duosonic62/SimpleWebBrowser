package com.example.WebApp.staticServerApp;

import com.example.webServer.HTTPRequest;
import com.example.webServer.HTTPResponse;
import com.example.webServer.Handler;

import java.io.File;
import java.io.IOException;

public class StaticWebAppHandler extends Handler {

    /**
     * GETが来た場合の処理
     * 静的なファイルを返却する
     *
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void doGet(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        File responseFile = new File(httpRequest.getRequestResouse());
        httpResponse.setResponseFile(responseFile);
        httpResponse.sendResponse(HTTPResponse.SC_OK, HTTPResponse.SD_OK);

    }

}
