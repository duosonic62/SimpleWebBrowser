package com.example.WebApp.bulletinBoardApp.dao;


import com.example.WebApp.bulletinBoardApp.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBからユーザ情報を取得するクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class UserDAO {
    private Connection connection;

//    /**
//     * コンストラクタ
//     * DBとのコネクトを得る
//     */
//    public UserDAO() throws Exception {
//        // コネクションを取得
//        connection = DBConnecter.getConnection();
//    }

    /**
     * ユーザ名に該当するユーザを取得するメソッド
     *
     * @return ユーザ
     */
    public UserEntity findUser(String userName) throws Exception {
        UserEntity userEntity = new UserEntity();
        ResultSet resultSet;

        try {
            // コネクションを取得
            connection = DBConnecter.getConnection();

            // SQLを作成
            String sql = "SELECT USER_ID, USER_NAME FROM USER WHERE USER_NAME = ?";
            // SQLをセット
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            // 結果があれば詰め込む
            if (resultSet.next()) {
                userEntity.setUserId(resultSet.getInt("USER_ID"));
                userEntity.setUserName(resultSet.getString("USER_NAME"));
            } else {
                //結果がなければnullで返す
                userEntity = null;
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

        return userEntity;
    }

    /**
     * ユーザ名に該当するユーザを作成するメソッド
     *
     * @return 登録ユーザ数
     */
    public Integer createUser(String userName) throws Exception {
        int count;
        try {
            // コネクションを取得
            connection = DBConnecter.getConnection();

            // SQLを作成
            String sql = "INSERT INTO USER (USER_NAME) VALUES (?)";
            // SQLをセット
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

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
