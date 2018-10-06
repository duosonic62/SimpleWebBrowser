package com.example.WebApp.bulletinBoardApp.dao;

import com.example.WebApp.bulletinBoardApp.entity.BulletinBoardEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DBから記事情報を取得するクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class BulletinBoardDAO {
    private Connection connection;

    /**
     * 全ての記事を取得するメソッド
     *
     * @return 記事
     */
    public List<BulletinBoardEntity> getAllItem() throws Exception {
        List<BulletinBoardEntity> bulletinBoardEntities = new ArrayList<>();
        // コネクションを取得
        connection = DBConnecter.getConnection();
        try {
            // SQL文が動的に変化することはないのでStatementを使用する
            Statement statement = connection.createStatement();

            // SQLを作成
            String sql = "SELECT ITEM_TITLE, ITEM_CONTENT, CREATE_DATE, USER_NAME\n" +
                    "FROM ITEM INNER JOIN USER ON ITEM.USER_ID=USER.USER_ID;";
            //SQLを実行
            ResultSet resultSet = statement.executeQuery(sql);

            // 結果を1レコードづつ詰め込む
            while (resultSet.next()) {
                BulletinBoardEntity bulletinBoardEntity = new BulletinBoardEntity();
                bulletinBoardEntity.setTitle(resultSet.getString("ITEM_TITLE"));
                bulletinBoardEntity.setContent(resultSet.getString("ITEM_CONTENT"));
                bulletinBoardEntity.setCreateDate(resultSet.getDate("CREATE_DATE"));
                bulletinBoardEntity.setUserName(resultSet.getString("USER_NAME"));

                bulletinBoardEntities.add(bulletinBoardEntity);
            }

            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // DBをクローズ
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bulletinBoardEntities;
    }




}
