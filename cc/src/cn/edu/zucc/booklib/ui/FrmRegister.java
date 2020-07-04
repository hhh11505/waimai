package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.*;
import cn.edu.zucc.booklib.control.MaManage;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.booklib.util.*;
import cn.edu.zucc.booklib.model.*;
import cn.edu.zucc.booklib.util.BaseException;

public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("×¢²á");
	private Button btnCancel = new Button("È¡Ïû");
	
	private JLabel labelUser = new JLabel("ÓÃ»§£º");
	private JLabel labelPwd = new JLabel("ÃÜÂë£º");
	private JLabel labelPwd2 = new JLabel("ÃÜÂë£º");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*MaManage sum=new MaManage();
		String ;
		String pwd=new String(this.edtPwd.getPassword());
		try {
			BeanManagerinfo user=sum.loadUser(mname);
			if(pwd.equals(user.getMpwd())){
			sum.currentUser=user;
				setVisible(false);*/
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			MaManage sum=new MaManage();
			String mname=this.edtUserId.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				BeanManagerinfo b=sum.reg(mname, pwd1, pwd2);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}
