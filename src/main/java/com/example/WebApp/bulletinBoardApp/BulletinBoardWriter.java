package com.example.WebApp.bulletinBoardApp;

import com.example.WebApp.bulletinBoardApp.entity.BulletinBoardEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 記事を生成するクラス
 *
 * @author rikusekiguchi 2018/07/07
 */
public class BulletinBoardWriter {

    /**
     * 掲示板のhtmlを生成するメソッド
     *
     * @param bulletinBoardEntities 記事のエンティティ
     * @return 生成したhtmlコード
     */
    public static List<String> createHtml(List<BulletinBoardEntity> bulletinBoardEntities){
        // htmlを格納するリストを作成
        List<String> htmlCode = new ArrayList<>();

        // ヘッダを挿入
        htmlCode.add("<!DOCTYPE html>");
        htmlCode.add("<html lang=\"en\">");
        htmlCode.add("<head>");
        htmlCode.add("<meta charset=\"UTF-8\">");
        htmlCode.add("<title>掲示板</title>");
        htmlCode.add("</head>");

        // ボディを挿入
        htmlCode.add("<body>");
        htmlCode.add("<h1> 掲示板 </h1>");
        htmlCode.add("<br>");
        htmlCode.add("<br>");

        // 記事を生成
        htmlCode.add("<table>");
        // 記事数分の繰り返し
        bulletinBoardEntities.stream()
                .forEach( item ->
                        {
                            htmlCode.add("<tr>");
                            htmlCode.add("<th colspan=\"3\">" + item.getTitle() + "</th>");
                            htmlCode.add("</tr>");
                            htmlCode.add("<tr>");
                            htmlCode.add("<td>       </td>");
                            htmlCode.add("<td>\"" + item.getCreateDate().toString() + "\"</td>");
                            htmlCode.add("<td>\"" + item.getUserName() + "\"</td>");
                            htmlCode.add("</tr>");
                            htmlCode.add("");
                            htmlCode.add("<tr>");
                            htmlCode.add("<td colspan=\"3\">" + item.getContent() + "</td>");
                            htmlCode.add("</tr>");
                        }
                );

        htmlCode.add("</table>");

        htmlCode.add("<br>");
        htmlCode.add("<form method=\"POST\" action=\"/BulletinBoard\" accept-charset=\"UTF-8\">");
        htmlCode.add("<table>");
        htmlCode.add("<tr> <td>Name</td>   <td><input type=\"text\" name=\"name\"></td></tr>");
        htmlCode.add("<tr><td>Title</td>  <td><input type=\"text\" name=\"title\"></td></tr>");
        htmlCode.add("<tr><td>Content</td><td><textarea name=\"content\" rows=\"10\" cols=\"40\">感想を記入します。</textarea></td></tr>");
        htmlCode.add("<tr><td></td><td><input type=\"submit\" value=\"送信する\"></td></tr>");
        htmlCode.add("</table>");
        htmlCode.add("</form>");

        htmlCode.add("</body>");
        htmlCode.add("</html>");

        return htmlCode;
    }
}
