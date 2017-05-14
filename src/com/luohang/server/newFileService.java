package com.luohang.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class newFileService {

   public static String newFileService(String Path) throws IOException{
	   
       
	   String nurl="http://localhost:8080/yunServlet/servlet/newFileServlet";
       URL Url = new URL(nurl);                
                
       String str ="Path="+Path;         
       
       HttpURLConnection HttpConn = (HttpURLConnection) Url.openConnection();                   
       HttpConn.setDoInput(true);
       HttpConn.setDoOutput(true); 
       HttpConn.setUseCaches(false);
       HttpConn.setRequestMethod("POST");
       HttpConn.setRequestProperty("Connection","Keep-Alive");
       HttpConn.setRequestProperty("Charset","UTF-8");
       HttpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");        
       HttpConn.setRequestProperty("Content-Length", String.valueOf(str.getBytes().length));        
       
       DataOutputStream os =new DataOutputStream (HttpConn.getOutputStream());        
       
       os.writeBytes(str); 
       os.flush();
              
       InputStream inst=HttpConn.getInputStream();  
       int chi;
       StringBuffer sb=new StringBuffer();
       while((chi=inst.read())!=-1){
     	  sb.append((char)chi);
       }
       os.close();
      return sb.toString();
   }
}
