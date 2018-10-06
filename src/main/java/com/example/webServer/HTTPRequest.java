package com.example.webServer;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * リクエストに対して処理を行なうクラス
 *
 * @author rikusekguchi 2018/07/07
 */
public class HTTPRequest {
    /**
     * リクエストを受け取るInputStream
     */
    private InputStream requestStream;

    /**
     * リクエスト全文
     */
    private List<String> requestLines;

    private final String COMMON_FILE_PATH = "src/main/resources";

    private String requestMethod;

    private String url;

    private String query;

    private String requestParam;

    private Map<String, String> requestParams;

    private String requestResouce;

    private String fileExtension;

    private String requestBody;

    /**
     * コンストラクタ
     *
     * @param requestStream リクエスト文
     */
    public HTTPRequest(InputStream requestStream) throws IOException {
        this.requestStream = requestStream;
        this.requestLines = getRequestLines();
        prepare();

    }

    /**
     * リクエスト全文を取得するメソッド
     */
    private List<String> getRequestLines() throws IOException {
        System.out.println();
        // リクエストの読み取りのために一度バッファーリーダに変換
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestStream));

        //返却用のリスト
        List<String> requests = new ArrayList<>();

        Integer contentLength = null;
        String line = bufferedReader.readLine();

        while (line != null && !"".equals(line)) {
            if (line.startsWith("Content-Length")) {
                contentLength = Integer.parseInt(line.split(" ")[1]);
            }
            requests.add(line);
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        // コンテンツがあれば読み込み
        if (contentLength != null) {
            char[] c = new char[contentLength];
            bufferedReader.read(c);
            this.requestBody = new String(c) + "\n";
            System.out.println(requestBody);
        } else {
            requestBody = null;
        }

        return requests;
    }

    /**
     * リクエストを解析するメソッド
     */
    private void prepare() {
        System.out.println("!!!!!");
        requestLines.stream().forEach(System.out::println);

        // メソッド
        String[] splits = requestLines.get(0).split(" ");
        requestMethod = splits[0];

        // url
        if (splits.length == 3) {
            if (splits[1].contains("?")) {
                url = splits[1].substring(0, splits[1].indexOf("?"));
            } else {
                url = splits[1].substring(0, splits[1].length());
            }
        } else {
            url = null;
        }

        // ファイルの拡張子
        fileExtension = url.substring(url.lastIndexOf(".") + 1,
                url.lastIndexOf(""));

        // ファイルへのパス
        requestResouce = COMMON_FILE_PATH + url;

        // クエリ
        if (requestMethod.equals("GET")) {
            query = splits[1].substring(splits[1].indexOf("?") + 1, splits[1].length());
        } else if (requestMethod.equals("POST")) {
            query = requestBody;
        }

        // リクエストパラメータ
        requestParams = new HashMap<>();
        List<String> params = new ArrayList<>();
        if (query.contains("&")) {
            params = Arrays.asList(query.split("&"));
        } else {
            params.add(query);
        }
        params.stream().forEach(param -> System.out.println("param : " + param));

        params.stream()
                .forEach(param ->
                {
                    try {
                        if (param.contains("=")) {
                            requestParams.put(param.split("=")[0],
                                    URLDecoder.decode(param.split("=")[1], "UTF-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getQuery() {
        return query;
    }

    public String getRequestParam(String key) {
        requestParam = requestParams.get(key);
        return requestParam;
    }

    public String getRequestResouse() {
        return requestResouce;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getRequestBody() {
        return requestBody;
    }
}