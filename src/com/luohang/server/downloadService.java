package com.luohang.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class downloadService {

   public String downloadService(String saveFilePath,ArrayList<String> filename ) throws IOException{
	    // 把要下载的文件的路径名转连接成字符串
	   try{
	   
	   String fileName=new String();
	   
	   for(int i=0;i<filename.size();i=i+2){
		   fileName=fileName+filename.get(i);
	   }	   
      
	   String str ="filenames="+fileName;         
	   
	   String actionUrl2="http://localhost:8080/yunServlet/servlet/downServlet?filenames="+fileName; 
	   URL url2=new URL(actionUrl2);
       
	   HttpURLConnection HttpConn = (HttpURLConnection) url2.openConnection();                   
       HttpConn.setDoInput(true);
       HttpConn.setDoOutput(true); 
       HttpConn.setUseCaches(false);
       HttpConn.setRequestMethod("POST");
       HttpConn.setRequestProperty("Connection","Keep-Alive");
       HttpConn.setRequestProperty("Charset","UTF-8");
       HttpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");        
       HttpConn.setRequestProperty("Content-Length", String.valueOf(str.getBytes().length));
	   
       DataOutputStream dos =new DataOutputStream (HttpConn.getOutputStream());        
       
       dos.writeBytes(str); 
       dos.flush();	         
     
       InputStream read=HttpConn.getInputStream(); 
      // BufferedReader read=new BufferedReader(new InputStreamReader(url2.openStream()));      
       String msg=null;
       int ch;
       StringBuffer b=new StringBuffer();
       
       int m=0;//记录“*”出现的次数。如果连续出现40个则认为是一个文件上传结束
       int k=1;//记录正在上传的文件个数,其对应的文件名是filename.get(k)
             
    	   while(k<filename.size())
		     {  FileOutputStream files=new FileOutputStream(saveFilePath+"\\"+filename.get(k));
		       while((ch=read.read())!=-1)
		       {
		     	  if((char)ch=='*')
		     	  {   
		     		m++;  	     	     
		     	  } 
		     	  //如果*出现的次数不足四十次。则证明不是该文本的结束，
		     	  //并把含有个数小于40的含有*的字符串写入文本，并把m清零
		     	  else if(m!=40)
		     	  {   for(int i=0;i<m;i++)
		     		     b.append('*');	     		  
		     		  b.append((char)ch);	     		  
		     		  m=0;
		     	  }
		     	  else break;
		       }
		         read.close();
		         
		       msg=b.toString();     
		       files.write(msg.getBytes());
		       files.flush();
		       files.close();
		       k=k+2;
	         }      
    	  
    	   dos.close();
    	   
    	   return "success";
	   }catch(Exception e){
		   return "error";
	   }
   }
}
