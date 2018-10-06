package com.example.WebApp.bulletinBoardApp.dao;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnecter {
    private static final String PROPERTY_FILE_PATH = "src/main/properties/database.properties";

    /**
     * DBとのコネクションを取得するメソッド
     * SQLのエクセプションのみ呼び出し元でハンドリングする
     *
     * @return コネクション
     */
    public static Connection getConnection() throws Exception {
        Connection connection = null;

        // プロパティからDB設定を読み込み
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(PROPERTY_FILE_PATH);
        properties.load(inputStream);

        // コネクションを取得取得
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );

        return connection;
    }
}

