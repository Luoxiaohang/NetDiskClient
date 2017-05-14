package com.luohang.show;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.luohang.errorMsg.infoTip;
import com.luohang.server.downloadService;
import com.luohang.server.newFileService;
import com.luohang.server.uploadServer;

public class enjoy implements ActionListener,MouseListener{
    JScrollPane jspmf,jspuf;
    JFrame jf;
    JTabbedPane tab ; 
    String[] filename=null;
    ArrayList<String> parentFile=new ArrayList<String>();//记录当前文件夹
    infoTip infotip;//弹出提示框
    int i=0;//记录上传文件的个数
    int l=0;//记录使用标签的个数
    int c=0;//记录使用复选框的个数
     //初始化第一张卡片
    Label[] mfile=new Label[15];//显示我的文件夹名字
    JCheckBox[] mfcb=new JCheckBox[15];//显示我的文件名字并附有选择框
    Panel jpmf,jpmfc,jpmfb;	 
    Button jdownload,jexit,jnewFile,jupPage;
    JFileChooser jdf;
	 
	 //初始化第二张卡片
    JButton jcf,upload,cancle,jexit2;
    JLabel[] upfile=new JLabel[15];//显示要上传的文件的名字
    File file;
    JFileChooser jfc;
    JPanel jpuf,jpufc,jpufb;
  
	public enjoy(String [] filelist,String parentfile){
		//获取文件夹的上一层目录
		parentFile.add(parentfile);		
		//用户的一阶文件目录以供下载使用
		filename=filelist;
		//处理第一张卡片
		jdownload=new Button("下载");
		jexit=new Button("退出");
		jpmf=new Panel();
		jpmf.setLayout(new BorderLayout());
		jpmfc=new Panel();
		jpmfc=new Panel(new GridLayout(15,1,0,20));
		for(int i=0;i<15;i++)
		{ 	
			//如果字符串非空筛选出一级菜单						
			 if(filelist!=null&&i<filelist.length)
			  {	 
				 mfcb[i]=new JCheckBox("");
				 mfile[i]=new Label("");
				 int j=0;//记录“\”出现的次数，出现一次的即为一级菜单
				 for(int k=0;k<filelist[i].length();k++)
				 { if(filelist[i].charAt(k)=='\\') 
				    j++;
				 }				 
				 if(j==1)				 				
				 {
					 if(filelist[i].contains("!!!"))
					 { String [] fname=filelist[i].split("!!!");
					   mfcb[c]=new JCheckBox(fname[1]);
					   jpmfc.add(mfcb[c]);
					   c++;
					 } if(filelist[i].contains("_"))
					 { 
						 String [] fname=filelist[i].split("_");
						 mfile[i]=new Label(fname[1]);
						 jpmfc.add(mfile[l]);
						 mfile[i].addMouseListener(this);
						 l++;
				      }			      
				  }
			   }
			 
			mfile[l]=new Label();
		}
		jspmf=new JScrollPane(jpmfc);		
		jpmf.add(jspmf,"Center");
		
		jpmfb=new Panel();
		jpmfb.setLayout(new GridLayout(1,4));
		jpmfb.add(jdownload);
		jdownload.addActionListener(this);
		jnewFile=new Button("新建文件夹");
		jpmfb.add(jnewFile);
		jnewFile.addActionListener(this);
		jupPage=new Button("返回");
		jupPage.addActionListener(this);
		jpmfb.add(jupPage);
		jpmfb.add(jexit);
		jexit.addActionListener(this);
		
		jpmf.add(jpmfb,"South");
		
		//处理第二张卡片
		jcf=new JButton("选择文件");
		cancle=new JButton("取消");
		jfc=new JFileChooser();
		jpuf=new JPanel();
		jpuf.setLayout(new BorderLayout());
		jpufc=new JPanel(new GridLayout(15,1,0,20));
		for(int i=0;i<10;i++)
		{
			upfile[i]=new JLabel(" ");
			jpufc.add(upfile[i]);
			
		}
		jspuf=new JScrollPane(jpufc);		
		jpuf.add(jspuf,"Center");
		
		jpufb=new JPanel();
		jpufb.setLayout(new GridLayout(2,2));
		jpufb.add(jcf);
		upload=new JButton("上传所有文件");
		jpufb.add(upload);
		upload.addActionListener(this);
		jcf.addActionListener(this);		
		jpufb.add(cancle);
		cancle.addActionListener(this);
		jexit2=new JButton("退出");
		jpufb.add(jexit2);
		jexit2.addActionListener(this);
		jpuf.add(jpufb,"South");
			
		jf=new JFrame("我的云盘");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        Container c=jf.getContentPane();
        tab = new JTabbedPane();
        tab.add("我的文件", jpmf);
	    tab.add("上传文件", jpuf);
		c.add(tab);
		jf.setSize(300,500); 
		jf.setResizable(false);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==jcf){
			int result=jfc.showSaveDialog(jf);			
			if(result==jfc.APPROVE_OPTION) 
			{	file=jfc.getSelectedFile();
			   String fileName=file.getPath();			   
			   upfile[i].setText(fileName);
			   i++;
            }
		}
		else if(e.getSource()==jexit||e.getSource()==jexit2){
			System.exit(0);
		}
		else if(e.getSource()==cancle){
			for(int i=0;i<10;i++)
			upfile[i].setText(" ");
			i=0;
		}
		else if(e.getSource()==jdownload){
			String filePath=new String();
			//获取文件的路径名
			for(int i=0;i<parentFile.size();i++)
			   {
				   filePath=filePath+parentFile.get(i);
			   }
			
			//获取要保存文件的路径名
			jdf=new JFileChooser();
			String saveFilePath=null;
			jdf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
			int result=jdf.showOpenDialog(jf);			
			if(result==jdf.APPROVE_OPTION) 
			{	file=jdf.getSelectedFile();
			    saveFilePath=file.getPath();			   
            }								
			//遍历复选框，把选中的复选框表示的文件名存入filenames中
			ArrayList<String> filenames=new ArrayList<String>();
			for(int i=0;i<mfcb.length;i++)
			 { if(mfcb[i].isSelected()&&!mfcb[i].getText().equals(""))
			    {
				  //遍历所有文件目录搜寻出当前文本的完整的路径
				   for(int j=0;j<filename.length;j++){
					 //如果文件目录包含复选框字符串且长度大于复选框字符串的长度，则把此文件目录存入filenames中
					   if(filename[j].length()>filePath.length()&&filename[j].substring(filePath.length()).contains(mfcb[i].getText()))
						   {		   
							   filenames.add(filename[j]+"---");
					           filenames.add(mfcb[i].getText());
						   }						   					   
				   }		
				}
			 }
			//如果选中的有文件则进行操作
			if(filenames.size()>0)
			{ downloadService down=new downloadService();		 
				try {
					String msg=down.downloadService(saveFilePath,filenames);
					if(msg.equals("success"))
					{
						infotip=new infoTip("下载成功！！");		
					}
					else {infotip=new infoTip("下载失败！！");}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
			
		}
		else if(e.getSource()==upload){
			//fileanmes存储要上传的文件名
			ArrayList<String> filenames=new ArrayList<String>();
			
			String filePath=new String();
			//filePath获取下载文件在服务器中的存储路径
			for(int i=0;i<parentFile.size();i++){
				filePath=filePath+parentFile.get(i);
				   }
			for(int i=0;i<10;i++){
				if(!upfile[i].getText().equals(" ")){
					filenames.add(filePath+"!!!"+upfile[i].getText());
				}
			}
			uploadServer uploads=new uploadServer();
			try {
				if(uploads.uploadServer(filenames).equals("successfuly")){
					infotip=new infoTip("上传成功！！");
				}
				else 
				{ infotip=new infoTip("上传失败！！");}
			} catch (IOException e1) {
				e1.printStackTrace();
			}					
		}
		else if(e.getSource()==jupPage){
			  //把所有复选框的文本内容清空
			for(int i=0;i<mfcb.length;i++)
					mfcb[i]=new JCheckBox("") ;		
			//清空jpmfc的组件并把复选框和标签的使用个数置零
			jpmfc.removeAll();
			l=0;
			c=0;
			//	判断保存在列表中的文件目录的长度如果大于一则ze将保存在列表中的最后一个元素移出以获取上一阶目录
			//否则不移出
			 if(parentFile.size()>1) 
			   parentFile.remove(parentFile.size()-1);			   
			    //获取完整路径
			    String filePath=new String();			    
			    for(int i=0;i<parentFile.size();i++){
			    	filePath=filePath+parentFile.get(i);
			    }
			    
			    //在所有文件目录中筛选出当前文件夹下子文件			    	
			    for(int i=0;i<filename.length;i++)
			    { 
			    	int j=0;//记录出现"\"的次数，如果出现一次则是下一阶目录
			    	//筛选出文件完整路径长度超目标文件路径长度且包含该文件路径的文件路径目录
			    	if(filename[i].length()>filePath.length()&&filename[i].contains(filePath))  	
			        { 
			    		for(int k=0;k<filename[i].substring(filePath.length()).length();k++)			       
			        	 {if(filename[i].substring(filePath.length()).charAt(k)=='\\') 
				           j++;			        	   
			        	 }
			    	//获取当前文件夹下的第一层目录	
					 if(j==1)				 				
					 {
						 //判断获取的文件目录是不是文件夹
						 if(filename[i].substring(filePath.length()).contains("!!!"))
							 { String [] fname=filename[i].substring(filePath.length()).split("!!!");
							   mfcb[c]=new JCheckBox(fname[1]);
							    jpmfc.add(mfcb[c]);
							   c++;
							 } 
						 else if(filename[i].substring(filePath.length()).contains("_"))
							 { 
								 String [] fname=filename[i].substring(filePath.length()).split("_");
								 mfile[l]=new Label(fname[1]);
								 jpmfc.add(mfile[l]);
								 mfile[l].addMouseListener(this);
								 l++;						 
						      }
				    }   
			       }
			    }
		    }	
		
		else if(e.getSource()==jnewFile){
			//获取当前文件夹的父文件夹在服务器中的路径
			try 
			{
			  if(filename!=null)
				  {
				  //filePath存储上传的文件的在服务器中的路径
				  String filePath=new String();
				   for(int i=0;i<parentFile.size();i++){
					   filePath=filePath+parentFile.get(i);
				   }  
				   //新建的文件名是851151_***的形式
				   String filename=filePath+"\\"+"851151_"+JOptionPane.showInputDialog("请输入新建文件名"); 					 
				   newFileService newFile=new newFileService();
				   if( newFile.newFileService(filename).equals("success"))
				   {
					   infotip=new infoTip("创建成功！！");							
				   }else 
				    { 
					   infotip=new infoTip("创建失败！！");
                    }
				 }
			  else{		
				  String filename=parentFile.get(0)+"\\"+"851151_"+JOptionPane.showInputDialog("请输入新建文件名");            							
				  newFileService newFile=new newFileService();
				  if( newFile.newFileService(filename).equals("success"))
				   {
					  infotip=new infoTip("创建成功！！");							
				   }else 
				    { 
					   infotip=new infoTip("创建失败！！");
                   }	
			   }
			 } catch (IOException e1) {
					e1.printStackTrace();
	    			}
			}
	}
	
	public void mouseClicked(MouseEvent e) {
		//鼠标点击两下打开文件夹
		if(e.getClickCount()==2)
		{	
			 //把所有复选框的文本内容清空
			for(int i=0;i<mfcb.length;i++)
				mfcb[i]=new JCheckBox("") ;	
			//清空jpmfc的组件并把复选框和标签的使用个数置零
			jpmfc.removeAll();
			l=0;
			c=0;
			String s=((Label) e.getSource()).getText();
		    //判断点击的是不是一个文件夹(如果获取的文本内容中含有“.”则认为不是文件夹，
			//如果是一个文件夹则响应并显示在该文件夹下的文件名，否则不响应
			if(!s.contains("."))		    
			 {  parentFile.add("\\"+"851151_"+s);//如果是文件夹则把文件夹名入栈进行保存			   
			    //获取完整路径
			    String filePath=new String();			    
			    for(int i=0;i<parentFile.size();i++){
			    	filePath=filePath+parentFile.get(i);
			    }
			    //在所有文件目录中筛选出当前文件夹下子文件
			    for(int i=0;i<filename.length;i++)
			    { 
			    	int j=0;//记录出现"\"的次数，以便获得下一阶目录
			    	//筛选出文件目录长度超过完整路径长度的文件目录
			    	if(filename[i].length()>filePath.length()&&filename[i].contains(s))  	
			        { 
			    		for(int k=0;k<filename[i].substring(filePath.length()).length();k++)			       
			        	 {if(filename[i].substring(filePath.length()).charAt(k)=='\\') 
				           j++;
			        	 }
			    	//获取当前文件夹下的第一层目录	
					 if(j==1)				 				
					 {
						 //判断获取的文件目录是不是文件夹
						 if(filename[i].substring(filePath.length()).contains("!!!"))
							 { String [] fname=filename[i].substring(filePath.length()).split("!!!");
							   mfcb[c]=new JCheckBox(fname[1]);							   
							   jpmfc.add(mfcb[c]);
							   c++;
							 } 
						 else if(filename[i].substring(filePath.length()).contains("_"))
							 { 
								 String [] fname=filename[i].substring(filePath.length()).split("_");
								 mfile[l]=new Label(fname[1]);
								 jpmfc.add(mfile[i]);
								 mfile[l].addMouseListener(this);
								 l++;						 
						     }
				    }   
			       }
			    }
		    }	
		}				
	}

	
	public void mousePressed(MouseEvent e) {
				
	}

	
	public void mouseReleased(MouseEvent e) {		
		
	}

	
	public void mouseEntered(MouseEvent e) {
				
	}

	
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	
	
	
}
