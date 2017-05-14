package com.luohang.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class uploadServer {
	
	public static String uploadServer (ArrayList<String> filenames) throws IOException{
		  String end="\r\n";
		  String twoHyphens="--";
		  String boundary="7de28d2b20c60";
		 
		  String actionUrl="http://localhost:8080/yunServlet/servlet/UploadServlet"; 
		  	
			    URL url=new URL(actionUrl);
			    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestMethod("POST");
                con.setRequestProperty("Connection","Keep-Alive");
                con.setRequestProperty("Charset","UTF-8");
                con.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
                DataOutputStream ds=new DataOutputStream(con.getOutputStream());               
               
                for(int i=0;i<filenames.size();i++){
                    String[] file=filenames.get(i).split("!!!");
	                String uploadFile =file[1];
	                String filename = uploadFile.substring(uploadFile.lastIndexOf("\\")+1);
	                ds.writeBytes(twoHyphens+boundary+end);
	                ds.writeBytes("Content-Disposition: form-data; "+
	                		 "name=\"file"+(i+1)+"\";" +" fileName=\""+file[0]+"!!!"+filename+"\""+end);
	                ds.writeBytes(end);
	               //读文件
	                FileInputStream fStream = new FileInputStream(uploadFile);
	                byte[] buffer=new byte[1024];
	                int length=-1;
	                while ((length = fStream.read(buffer))!=-1)
	                   {
	                        ds.write(buffer,0,length);                    
	                   }
	                ds.writeBytes(end);
	                fStream.close();
                }
                //写文件结束               
              ds.writeBytes(twoHyphens+boundary + twoHyphens +end);
              ds.flush();
              
            //  BufferedReader ins=new BufferedReader(new InputStreamReader(url.openStream())); 
              InputStream ins=con.getInputStream();  
              int ch;
              StringBuffer bs=new StringBuffer();
              while((ch=ins.read())!=-1){
            	  bs.append((char)ch);
              }
     	      ds.close();
     	     return bs.toString();
				
	}
	
}
