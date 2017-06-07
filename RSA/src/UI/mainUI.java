package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bean.message;
import doRSA.Sig;
import doRSA.check;
import doRSA.doMD5;

public class mainUI extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	JLabel     JL1 =new JLabel("RSA数字签名");
	JLabel     JL2 =new JLabel("请输入原文:");
	JLabel     JL3 =new JLabel("n:");
	JLabel     JL4 =new JLabel("e:");
	JLabel     JL5 =new JLabel("Sig(m):");
	JLabel     JL6 =new JLabel("请选择文件:");
	JLabel     JL7 =new JLabel("m:");
	JLabel     JL8 =new JLabel("长文/文件MD5：");
	JButton    JB1 =new JButton("进行文字签名");
	JButton    JB2 =new JButton("浏览..");
	JButton	   JB3 =new JButton("进行文件签名");
	JButton	   JB4=new JButton("签名验证");
	JTextArea  JTa1=new JTextArea();//m
	JTextField JTb2=new JTextField();//n
	JTextField JTb3=new JTextField();//e
	JTextField JTb4=new JTextField();//Sig(m)
	JTextField JTb5=new JTextField();//文件路径
	JTextArea  JTa6=new JTextArea();//解密m
	JTextField JTb7=new JTextField();//MD5
	JFileChooser chooser = new JFileChooser();//文件选择
	message mes;
	String md5;
	check ch;
	doMD5 md;
	public mainUI(){
		this.setTitle("数字签名系统");
		this.setLayout(null);

		JL1.setBounds(290,25,300,40);
		JL1.setFont(new Font("",1,20));
		this.add(JL1);
		
		JL2.setBounds(50,60,300,40);
		JL2.setFont(new Font("",1,15));
		this.add(JL2);
		
		JL3.setBounds(50,300,50,30);
		JL3.setFont(new Font("",1,15));
		this.add(JL3);	
		
		JL4.setBounds(50,330,50,30);
		JL4.setFont(new Font("",1,15));
		this.add(JL4);	

		JL5.setBounds(50,360,50,30);
		JL5.setFont(new Font("",1,15));
		this.add(JL5);
		
		JL6.setBounds(50,235,300,40);
		JL6.setFont(new Font("",1,15));
		this.add(JL6);
		
		JL7.setBounds(50,415,300,40);
		JL7.setFont(new Font("",1,15));
		this.add(JL7);
		
		JL8.setBounds(175,265,120,40);
		JL8.setFont(new Font("",1,15));
		this.add(JL8);
		
		JB1.setBounds(50,210,120,25);
		JB1.addActionListener(this);
		this.add(JB1);
		
		JB2.setBounds(630,235,70,25);
		JB2.addActionListener(this);
		this.add(JB2);
		
		JB3.setBounds(50,270,120,25);
		JB3.addActionListener(this);
		this.add(JB3);
		
		JB4.setBounds(50,390,120,25);
		JB4.addActionListener(this);
		this.add(JB4);
		
		JTa1.setBounds(50,100,650,100);
		JTa1.setFont(new Font("",1,15));
		JTa1.setLineWrap(true);
		JTa1.setWrapStyleWord(true);
	/*	JScrollPane pane=new JScrollPane(JTa1); 
		this.add(pane);*/
	    this.add(JTa1);
		
		JTb2.setBounds(105,300,595,22);
		JTb2.setFont(new Font("",1,15));
		JTb2.setEditable(false);
		this.add(JTb2);

		JTb3.setBounds(105,330,595,22);
		JTb3.setFont(new Font("",1,15));
		JTb3.setEditable(false);
		this.add(JTb3);

		JTb4.setBounds(105,360,595,22);
		JTb4.setFont(new Font("",1,15));
		JTb4.setEditable(false);
		this.add(JTb4);
		
		JTb5.setBounds(140,240,480,22);
		JTb5.setFont(new Font("",1,15));
		this.add(JTb5);
		
		JTa6.setBounds(50,455,650,88);
		JTa6.setFont(new Font("",1,15));
		JTa6.setLineWrap(true);
		JTa6.setWrapStyleWord(true);
		JTa6.setEditable(false);
		this.add(JTa6);
		
		JTb7.setBounds(295,270,405,22);
		JTb7.setFont(new Font("",1,15));
		JTb7.setEditable(false);
		this.add(JTb7);
		
		this.setVisible(true);
		this.setBounds(400,50,760,600);
		this.setResizable(false);
		addWindowListener(new WindowAdapter()
        { public void windowClosing(WindowEvent e)
             {
                 System.exit(0);
             }
        });
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//进行文字签名
		if(e.getSource()==JB1){
			Sig sig=new Sig();
			String m;
			String z;
			try {
				m = java.net.URLEncoder.encode(JTa1.getText(),"utf-8");
				byte[] bBase=m.getBytes();
				
			if(bBase.length>=56){
					md=new doMD5();
					z=md.zhaiyao(m);
					JTb7.setText(z);
				}else{
					z=m;
					JTb7.setText("");
				}
				mes=sig.Siger(new BigInteger(z.getBytes()));
				JTb2.setText(mes.getN().toString());
				JTb3.setText(mes.getE().toString());
				JTb4.setText(mes.getSigm().toString());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		//浏览
		if(e.getSource()==JB2){
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int status = chooser.showOpenDialog(null);
			if(status==1){
				return;
			}else{
				File file=chooser.getSelectedFile();
				JTb5.setText(file.getPath());
				md=new doMD5();
				md5=md.zhaiyao(file);
				JTb7.setText(md5);
				return;
			}
		}
		if(e.getSource()==JB3){
			Sig sig=new Sig();
			String m;
			if(JTb7.getText().equals(""))return;
			try {
				m = java.net.URLEncoder.encode(JTb7.getText(),"utf-8");
				mes=sig.Siger(new BigInteger(m.getBytes()));
				JTb2.setText(mes.getN().toString());
				JTb3.setText(mes.getE().toString());
				JTb4.setText(mes.getSigm().toString());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		//进行检验
		if(e.getSource()==JB4){
			ch=new check();
			BigInteger mm=ch.checker(mes);
			try {
				JTa6.setText(java.net.URLDecoder.decode(new String(mm.toByteArray()),   "utf-8"));
				if(JTa6.getText().equals(JTa1.getText())|JTa6.getText().equals(JTb7.getText())){
					JL7.setText("m: 验证安全");
				}else{
					JL7.setText("m: 验证失败，发生改动");
				}
				return;
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
