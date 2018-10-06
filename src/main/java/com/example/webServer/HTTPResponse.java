package com.example.webServer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.*;
import java.util.List;


/**
 * レスポンスの生成と送信を行なうクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class HTTPResponse {
    /**
     * レスポンスを生成するoutputStream
     */
    OutputStream responseStream;

    /**
     * レスポンスファイル
     */
    private File responseFile;

    /**
     * リクエストが正常な場合のステータスコード
     */
    public static final int SC_OK = 200;

    /**
     * リクエストが正常な場合のステータス説明
     */
    public static final String SD_OK = "OK";

    /**
     * リクエストで要求されたファイルが見つからない場合のステータスコード
     */
    public static final int SC_NOT_FOUND = 404;

    /**
     * リクエストで要求されたファイルが見つからない場合のステータス説明
     */
    public static final String SD_NOT_FOUND = "Not Found";

    /**
     * サーバ内部でエラーがあった場合のステータスコード
     */
    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    /**
     * サーバ内部でエラーがあった場合のステータス説明
     */
    public static final String SD_INTERNAL_SERVER_ERROR = "Internal Sever Error";


    /**
     * コンストラクタ
     *
     * @param responseStream レスポンス文
     */
    public HTTPResponse(OutputStream responseStream) {
        this.responseStream = responseStream;
    }


    /**
     * レスポンスの送信を行なうメソッド
     *
     * @param statusCode        ステータスコード
     * @param statusDescription ステータス情報
     */
    public void sendResponse(int statusCode, String statusDescription) throws IOException {
        DataOutputStream dataOutputStream   = new DataOutputStream(responseStream);
        FileDataSource fileDataSource       = new FileDataSource(responseFile);
        DataHandler dataHandler             = new javax.activation.DataHandler(fileDataSource);


        // レスポンスヘッダの生成
        String responseHead = ("HTTP/1.1 " + statusCode + " " + statusDescription + "\n" +
                "Content-Type: " + fileDataSource.getContentType() + "\n" +"\n");

        // 書き込み
        dataOutputStream.write(responseHead.getBytes());
        dataHandler.writeTo(dataOutputStream);
        dataOutputStream.flush();
        dataOutputStream.close();

    }

    /**
     * レスポンスの送信を行なうメソッド
     *
     * @param statusCode        ステータスコード
     * @param statusDescription ステータス情報
     *
     */
    public void sendResponse(int statusCode, String statusDescription, List<String> source) throws IOException {
        DataOutputStream dataOutputStream   = new DataOutputStream(responseStream);

        // レスポンスヘッダの生成
        String responseHead = ("HTTP/1.1 " + statusCode + " " + statusDescription + "\n" +
                "Content-Type:text/html" + "\n" +"\n");

        // 出力を書き込んで行く
        dataOutputStream.write(responseHead.getBytes());
        for (String str : source){
            dataOutputStream.write(str.getBytes());
        }
        dataOutputStream.flush();
        dataOutputStream.close();
    }


    public void setResponseFile(File responseFile) {
        this.responseFile = responseFile;
    }
}
