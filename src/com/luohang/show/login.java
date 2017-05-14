package com.luohang.show;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.luohang.errorMsg.infoTip;
import com.luohang.server.loginServer;

public class login implements ActionListener {
      
	 JPanel jpc=new JPanel();
	 JPanel jps=new JPanel();
	 JPanel jpn=new JPanel();
	 JTextField jtf=new JTextField();
	 JPasswordField jpf=new JPasswordField();
	 JButton jregist=new JButton("◊¢≤·");
	 JButton jlogin=new JButton("µ«¬º");
	 JButton jexit=new JButton("ÕÀ≥ˆ");
	 JLabel jcl=new JLabel("’Àªß",JLabel.CENTER);
	 JLabel jpl=new JLabel("√‹¬Î",JLabel.CENTER);
	 JFrame jf=new JFrame("Œ“µƒ‘∆≈Ã");
	
	 public static void main(String[] args) {
        login logins=new login();
	}
   
	public  login(){
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c=jf.getContentPane();
        c.setLayout(new BorderLayout());
	            
        jpc.setLayout(new GridLayout(2,2));
		jpc.add(jcl);
	    jpc.add(jtf);
		jpc.add(jpl);
		jpc.add(jpf);
		c.add(jpc,"Center");
				
		jps.setLayout(new GridLayout (1,3));
        jregist.addActionListener(this);
		jps.add(jregist);
		jlogin.addActionListener(this);
		jps.add(jlogin);
		jexit.addActionListener(this);
		jps.add(jexit);		
		c.add(jps,"South");
		jf.setSize(300,130);
	    jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jregist){
			registUser reg=new registUser();
		}		
		else if(e.getSource()==jexit){
			System.exit(0);
		}
		else if(e.getSource()==jlogin){
			try {
				@SuppressWarnings({ "static-access", "deprecation" })
				String msg=new loginServer().loginServer(jtf.getText(),jpf.getText());
				
				if(msg.equals("nofile"))
				{
				String [] filelist=null;					
				enjoy enjoy=new enjoy(filelist,jtf.getText());
				jf.setVisible(false);
				}
				else if(msg.equals("noUser")) 
				{
					infoTip error=new infoTip("ƒ˙ ‰»Îµƒ’ ∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°");
				}
				else 
				{
				String [] filelist=msg.split("!!!\n");					
				enjoy enjoy=new enjoy(filelist,jtf.getText());
				jf.setVisible(false);
				}			    
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
	}
		
}
