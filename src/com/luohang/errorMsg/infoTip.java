package com.luohang.errorMsg;

import javax.swing.JOptionPane;

public class infoTip {

	public static void main(String[] args) {
      infoTip e=new infoTip("����");
	}
    public infoTip(String msg){
    	JOptionPane.showMessageDialog(null,msg);
    }
}
