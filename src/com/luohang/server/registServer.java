package com.luohang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.luohang.bean.user;

public class registServer {

	public String  registServer(user user) throws IOException 
	{
		   String url="http://localhost:8080/yunServlet/servlet/RegistServlet?username="
	                +user.getName()+"&password="+user.getPw()+"&password2="+user.getConfimpw()
	                +"&nickname="+user.getNickName()+"&email="+user.getEmail();
	       URL url2=new URL(url); 
	       URLConnection conn= url2.openConnection();     
	       BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));      
	       
	       BufferedReader read=new BufferedReader(new InputStreamReader(url2.openStream()));      
	       String s=null;
	       s=read.readLine();    
	       read.close();
	       reader.close();
	       return s;
		}
	}

