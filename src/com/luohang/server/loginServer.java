package com.luohang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class loginServer {
    
	public static String loginServer(String name,String password) throws IOException{
       String url="http://localhost:8080/yunServlet/servlet/LoginServlet?username="+name+"&password="+password;
       URL url2=new URL(url); 
       URLConnection conn= url2.openConnection();     
       BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));      
       
       BufferedReader read=new BufferedReader(new InputStreamReader(url2.openStream()));      
       String msg=null;
       int ch;
       StringBuffer b=new StringBuffer();
       while((ch=read.read())!=-1)
       {
     	  b.append((char)ch);
       }
       read.close();
       reader.close();
       msg=b.toString();
       return msg;
	}
}