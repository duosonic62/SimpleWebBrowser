package com.example.WebApp.bulletinBoardApp.dao;

import com.example.WebApp.bulletinBoardApp.entity.ItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * DBの記事情報を操作するクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class ItemDAO {
    private Connection connection;

    /**
     * 記事を登録するメソッド
     *
     * @return 登録記事数
     */
    public Integer createItem(ItemEntity itemEntity) throws Exception{
        int count;
        try {
            // コネクションを取得
            connection = DBConnecter.getConnection();

            // SQLを作成
            String sql = "INSERT INTO ITEM (ITEM_TITLE, ITEM_CONTENT, USER_ID, CREATE_DATE)" +
                    " VALUES (?, ?, ?, ?)";

            System.out.println(itemEntity.getUserId());
            // SQLをセット
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, itemEntity.getTitle());
            preparedStatement.setString(2, itemEntity.getContent());
            preparedStatement.setInt(3, 1);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            // SQLを実行して更新件数を取得
            count = preparedStatement.executeUpdate();
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

        return count;
    }

}
