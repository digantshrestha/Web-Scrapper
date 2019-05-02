package com.pro.app;

import com.pro.app.util.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class Program{
    public static void main(String[] args) {
        try{
            HttpClient http=new HttpClient();
            String content=http.get("http://cybersansar.com/thumbnail_view.php?gal_id=2227");
            String regEx="graphics/model/(.*?).jpg";
            Pattern pattern=Pattern.compile(regEx);
            Matcher matcher=pattern.matcher(content);

            while(matcher.find()){   
                String link=matcher.group();
                //System.out.println("crawling..."+ link);
                String imgLink="http://cybersansar.com/"+link.replace("thumb/","");
                System.out.println(imgLink);
                URL url=new URL(imgLink);
                URLConnection conn=url.openConnection();
                String[] token=imgLink.split("/");
                String name=token[token.length-1];
                InputStream is=conn.getInputStream();
                OutputStream os=new FileOutputStream("e:/MyFiles/photos/"+name);

                byte[] data=new byte[1024*5];
                int i=0;
                while((i=is.read(data))!=-1){
                    os.write(data, 0, i);
                }
                is.close();
                os.close();
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}