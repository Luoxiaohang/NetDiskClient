package com.luohang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class logoutService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	  public static String logoutService(String name,String password) throws IOException{
	       String url="http://localhost:8080/yunServlet/servlet/LogoutServlet?username="+name;
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
