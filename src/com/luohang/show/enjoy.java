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
    ArrayList<String> parentFile=new ArrayList<String>();//��¼��ǰ�ļ���
    infoTip infotip;//������ʾ��
    int i=0;//��¼�ϴ��ļ��ĸ���
    int l=0;//��¼ʹ�ñ�ǩ�ĸ���
    int c=0;//��¼ʹ�ø�ѡ��ĸ���
     //��ʼ����һ�ſ�Ƭ
    Label[] mfile=new Label[15];//��ʾ�ҵ��ļ�������
    JCheckBox[] mfcb=new JCheckBox[15];//��ʾ�ҵ��ļ����ֲ�����ѡ���
    Panel jpmf,jpmfc,jpmfb;	 
    Button jdownload,jexit,jnewFile,jupPage;
    JFileChooser jdf;
	 
	 //��ʼ���ڶ��ſ�Ƭ
    JButton jcf,upload,cancle,jexit2;
    JLabel[] upfile=new JLabel[15];//��ʾҪ�ϴ����ļ�������
    File file;
    JFileChooser jfc;
    JPanel jpuf,jpufc,jpufb;
  
	public enjoy(String [] filelist,String parentfile){
		//��ȡ�ļ��е���һ��Ŀ¼
		parentFile.add(parentfile);		
		//�û���һ���ļ�Ŀ¼�Թ�����ʹ��
		filename=filelist;
		//�����һ�ſ�Ƭ
		jdownload=new Button("����");
		jexit=new Button("�˳�");
		jpmf=new Panel();
		jpmf.setLayout(new BorderLayout());
		jpmfc=new Panel();
		jpmfc=new Panel(new GridLayout(15,1,0,20));
		for(int i=0;i<15;i++)
		{ 	
			//����ַ����ǿ�ɸѡ��һ���˵�						
			 if(filelist!=null&&i<filelist.length)
			  {	 
				 mfcb[i]=new JCheckBox("");
				 mfile[i]=new Label("");
				 int j=0;//��¼��\�����ֵĴ���������һ�εļ�Ϊһ���˵�
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
		jnewFile=new Button("�½��ļ���");
		jpmfb.add(jnewFile);
		jnewFile.addActionListener(this);
		jupPage=new Button("����");
		jupPage.addActionListener(this);
		jpmfb.add(jupPage);
		jpmfb.add(jexit);
		jexit.addActionListener(this);
		
		jpmf.add(jpmfb,"South");
		
		//����ڶ��ſ�Ƭ
		jcf=new JButton("ѡ���ļ�");
		cancle=new JButton("ȡ��");
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
		upload=new JButton("�ϴ������ļ�");
		jpufb.add(upload);
		upload.addActionListener(this);
		jcf.addActionListener(this);		
		jpufb.add(cancle);
		cancle.addActionListener(this);
		jexit2=new JButton("�˳�");
		jpufb.add(jexit2);
		jexit2.addActionListener(this);
		jpuf.add(jpufb,"South");
			
		jf=new JFrame("�ҵ�����");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        Container c=jf.getContentPane();
        tab = new JTabbedPane();
        tab.add("�ҵ��ļ�", jpmf);
	    tab.add("�ϴ��ļ�", jpuf);
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
			//��ȡ�ļ���·����
			for(int i=0;i<parentFile.size();i++)
			   {
				   filePath=filePath+parentFile.get(i);
			   }
			
			//��ȡҪ�����ļ���·����
			jdf=new JFileChooser();
			String saveFilePath=null;
			jdf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
			int result=jdf.showOpenDialog(jf);			
			if(result==jdf.APPROVE_OPTION) 
			{	file=jdf.getSelectedFile();
			    saveFilePath=file.getPath();			   
            }								
			//������ѡ�򣬰�ѡ�еĸ�ѡ���ʾ���ļ�������filenames��
			ArrayList<String> filenames=new ArrayList<String>();
			for(int i=0;i<mfcb.length;i++)
			 { if(mfcb[i].isSelected()&&!mfcb[i].getText().equals(""))
			    {
				  //���������ļ�Ŀ¼��Ѱ����ǰ�ı���������·��
				   for(int j=0;j<filename.length;j++){
					 //����ļ�Ŀ¼������ѡ���ַ����ҳ��ȴ��ڸ�ѡ���ַ����ĳ��ȣ���Ѵ��ļ�Ŀ¼����filenames��
					   if(filename[j].length()>filePath.length()&&filename[j].substring(filePath.length()).contains(mfcb[i].getText()))
						   {		   
							   filenames.add(filename[j]+"---");
					           filenames.add(mfcb[i].getText());
						   }						   					   
				   }		
				}
			 }
			//���ѡ�е����ļ�����в���
			if(filenames.size()>0)
			{ downloadService down=new downloadService();		 
				try {
					String msg=down.downloadService(saveFilePath,filenames);
					if(msg.equals("success"))
					{
						infotip=new infoTip("���سɹ�����");		
					}
					else {infotip=new infoTip("����ʧ�ܣ���");}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
			
		}
		else if(e.getSource()==upload){
			//fileanmes�洢Ҫ�ϴ����ļ���
			ArrayList<String> filenames=new ArrayList<String>();
			
			String filePath=new String();
			//filePath��ȡ�����ļ��ڷ������еĴ洢·��
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
					infotip=new infoTip("�ϴ��ɹ�����");
				}
				else 
				{ infotip=new infoTip("�ϴ�ʧ�ܣ���");}
			} catch (IOException e1) {
				e1.printStackTrace();
			}					
		}
		else if(e.getSource()==jupPage){
			  //�����и�ѡ����ı��������
			for(int i=0;i<mfcb.length;i++)
					mfcb[i]=new JCheckBox("") ;		
			//���jpmfc��������Ѹ�ѡ��ͱ�ǩ��ʹ�ø�������
			jpmfc.removeAll();
			l=0;
			c=0;
			//	�жϱ������б��е��ļ�Ŀ¼�ĳ����������һ��ze���������б��е����һ��Ԫ���Ƴ��Ի�ȡ��һ��Ŀ¼
			//�����Ƴ�
			 if(parentFile.size()>1) 
			   parentFile.remove(parentFile.size()-1);			   
			    //��ȡ����·��
			    String filePath=new String();			    
			    for(int i=0;i<parentFile.size();i++){
			    	filePath=filePath+parentFile.get(i);
			    }
			    
			    //�������ļ�Ŀ¼��ɸѡ����ǰ�ļ��������ļ�			    	
			    for(int i=0;i<filename.length;i++)
			    { 
			    	int j=0;//��¼����"\"�Ĵ������������һ��������һ��Ŀ¼
			    	//ɸѡ���ļ�����·�����ȳ�Ŀ���ļ�·�������Ұ������ļ�·�����ļ�·��Ŀ¼
			    	if(filename[i].length()>filePath.length()&&filename[i].contains(filePath))  	
			        { 
			    		for(int k=0;k<filename[i].substring(filePath.length()).length();k++)			       
			        	 {if(filename[i].substring(filePath.length()).charAt(k)=='\\') 
				           j++;			        	   
			        	 }
			    	//��ȡ��ǰ�ļ����µĵ�һ��Ŀ¼	
					 if(j==1)				 				
					 {
						 //�жϻ�ȡ���ļ�Ŀ¼�ǲ����ļ���
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
			//��ȡ��ǰ�ļ��еĸ��ļ����ڷ������е�·��
			try 
			{
			  if(filename!=null)
				  {
				  //filePath�洢�ϴ����ļ����ڷ������е�·��
				  String filePath=new String();
				   for(int i=0;i<parentFile.size();i++){
					   filePath=filePath+parentFile.get(i);
				   }  
				   //�½����ļ�����851151_***����ʽ
				   String filename=filePath+"\\"+"851151_"+JOptionPane.showInputDialog("�������½��ļ���"); 					 
				   newFileService newFile=new newFileService();
				   if( newFile.newFileService(filename).equals("success"))
				   {
					   infotip=new infoTip("�����ɹ�����");							
				   }else 
				    { 
					   infotip=new infoTip("����ʧ�ܣ���");
                    }
				 }
			  else{		
				  String filename=parentFile.get(0)+"\\"+"851151_"+JOptionPane.showInputDialog("�������½��ļ���");            							
				  newFileService newFile=new newFileService();
				  if( newFile.newFileService(filename).equals("success"))
				   {
					  infotip=new infoTip("�����ɹ�����");							
				   }else 
				    { 
					   infotip=new infoTip("����ʧ�ܣ���");
                   }	
			   }
			 } catch (IOException e1) {
					e1.printStackTrace();
	    			}
			}
	}
	
	public void mouseClicked(MouseEvent e) {
		//��������´��ļ���
		if(e.getClickCount()==2)
		{	
			 //�����и�ѡ����ı��������
			for(int i=0;i<mfcb.length;i++)
				mfcb[i]=new JCheckBox("") ;	
			//���jpmfc��������Ѹ�ѡ��ͱ�ǩ��ʹ�ø�������
			jpmfc.removeAll();
			l=0;
			c=0;
			String s=((Label) e.getSource()).getText();
		    //�жϵ�����ǲ���һ���ļ���(�����ȡ���ı������к��С�.������Ϊ�����ļ��У�
			//�����һ���ļ�������Ӧ����ʾ�ڸ��ļ����µ��ļ�����������Ӧ
			if(!s.contains("."))		    
			 {  parentFile.add("\\"+"851151_"+s);//������ļ�������ļ�������ջ���б���			   
			    //��ȡ����·��
			    String filePath=new String();			    
			    for(int i=0;i<parentFile.size();i++){
			    	filePath=filePath+parentFile.get(i);
			    }
			    //�������ļ�Ŀ¼��ɸѡ����ǰ�ļ��������ļ�
			    for(int i=0;i<filename.length;i++)
			    { 
			    	int j=0;//��¼����"\"�Ĵ������Ա�����һ��Ŀ¼
			    	//ɸѡ���ļ�Ŀ¼���ȳ�������·�����ȵ��ļ�Ŀ¼
			    	if(filename[i].length()>filePath.length()&&filename[i].contains(s))  	
			        { 
			    		for(int k=0;k<filename[i].substring(filePath.length()).length();k++)			       
			        	 {if(filename[i].substring(filePath.length()).charAt(k)=='\\') 
				           j++;
			        	 }
			    	//��ȡ��ǰ�ļ����µĵ�һ��Ŀ¼	
					 if(j==1)				 				
					 {
						 //�жϻ�ȡ���ļ�Ŀ¼�ǲ����ļ���
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
