package com.pro.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient{
    public String get(String link) throws IOException{
        URL url=new URL(link);
        URLConnection conn=url.openConnection();
        String agent="Mozilla/5.0 (Windows NT 10.0)"+ 
        "AppleWebKit/537.36 (KHTML, like Gecko)" +
        "Chrome/73.0.3683.103 Safari/537.36";
        conn.setRequestProperty("User-Agent", agent);

        BufferedReader reader=new BufferedReader(
            new InputStreamReader(conn.getInputStream()));

        String line="";

        StringBuilder content=new StringBuilder();
        while((line=reader.readLine())!=null){
            content.append(line).append("\r\n");
        }
        reader.close();
        return content.toString();
    }
}