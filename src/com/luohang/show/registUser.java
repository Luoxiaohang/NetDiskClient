package com.luohang.show;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.luohang.bean.user;
import com.luohang.errorMsg.infoTip;
import com.luohang.server.registServer;

public class registUser implements ActionListener{

	JPanel jpn,jpc,jps;
	JLabel name,pw,confimpw,nickName,email;
	JTextField nameJf,pwJf,confimpwJf,nickNameJf,emailJf;
	JButton reset,confim;
    user userBean=new user();
	
	public   registUser() {
		JFrame jf=new JFrame("ª∂”≠◊¢≤·");
		Container c=jf.getContentPane();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(280,250);		
		
		jpc=new JPanel(new GridLayout(5,2));		
		name=new JLabel("–’√˚",JLabel.CENTER);
		pw=new JLabel("√‹¬Î",JLabel.CENTER);
		confimpw=new JLabel("»∑»œ√‹¬Î",JLabel.CENTER);
		nickName=new JLabel("Í«≥∆",JLabel.CENTER);
		email=new JLabel("” œ‰",JLabel.CENTER);
		nameJf=new JTextField();
		pwJf=new JTextField();
		confimpwJf=new JTextField();
		nickNameJf=new JTextField();
		emailJf=new JTextField();
		jpc.add(name);
		jpc.add(nameJf);
		jpc.add(pw);
		jpc.add(pwJf);
		jpc.add(confimpw);
		jpc.add(confimpwJf);
		jpc.add(nickName);
		jpc.add(nickNameJf);
		jpc.add(email);
		jpc.add(emailJf);
		c.add(jpc,"Center");
		
		reset=new JButton("÷ÿ÷√");
		reset.addActionListener(this);
		confim=new JButton("»∑»œ");
		confim.addActionListener(this);
		jps=new JPanel();
		jps.setLayout(new GridLayout(1,2));
		jps.add(reset);
		jps.add(confim);
		c.add(jps,"South");
		
		jf.setVisible(true);

	}


	public void actionPerformed(ActionEvent e) {
      if(e.getSource()==reset)
		{
    	  nameJf.setText("");
    	  pwJf.setText("");
    	  confimpwJf.setText("");
    	  nickNameJf.setText("");
    	  emailJf.setText(""); 	  
    	  }
      if(e.getSource()==confim)
    	  {
        userBean.setName(nameJf.getText());
   	    userBean.setPw(pwJf.getText());
   	    userBean.setConfimpw(confimpwJf.getText());
   	    userBean.setNickName(nickNameJf.getText());
   	    userBean.setEmail(emailJf.getText());
   	    try {
			registServer regs=new registServer();
			String err=regs.registServer(userBean);
			if(err!=null){
				infoTip infotip =new infoTip(err);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  }
	}

}
