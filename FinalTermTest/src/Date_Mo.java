import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Date_Mo extends JFrame{
	int year, month, date, check;
	String user;
	String doit, memo;
	
	JPanel ListP;
	JLabel ListL;
	JButton ListAdd;
	
	JPanel inListP;
	JCheckBox ListC;
	JTextField ListTF;
	JButton Delete;
	JScrollPane ListS;
	
	JPanel MemoP;
	JLabel MemoL;
	JTextArea MemoA;
	JScrollPane MemoS;
	
	Calendal_Mo Calendal = new Calendal_Mo();
	Login_Mo Login = new Login_Mo();
	
	public Date_Mo(){
		this.setTitle(Calendal.year+"/"+Calendal.month+"/"+Calendal.date);
		this.setSize(350,400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());//상하 분리
		//
		ListP = new JPanel();
		ListP.setSize(300,200);
		ListP.setLayout(new BorderLayout());
		
		ListL = new JLabel("Do it List");
		ListP.add(ListL, BorderLayout.SOUTH);
		
		ListAdd = new JButton("+");
		ListP.add(ListAdd,BorderLayout.SOUTH);
		//
		inListP = new JPanel();
		inListP.setSize(300,180);
		inListP.setLayout(new FlowLayout());
		ListP.add(inListP,BorderLayout.CENTER);
		
		ListC = new JCheckBox();
		inListP.add(ListC);
		
		ListTF = new JTextField(15);
		inListP.add(ListTF);
		
		Delete = new JButton("-");
		inListP.add(Delete);
		
		ListS = new JScrollPane();
		ListS.setViewportView(inListP);
		//
		MemoP = new JPanel();
		MemoP.setSize(300,200);
		MemoP.setLayout(new BorderLayout());
		
		MemoL = new JLabel("Memo");
		MemoP.add(MemoL,BorderLayout.SOUTH);
		
		MemoA = new JTextArea();
		MemoP.add(MemoA,BorderLayout.CENTER);
		//
		this.add(ListP);
		this.add(MemoP);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//끝내면 저장하는 방식으로
		this.setVisible(true);
	}
	
	public void usercheck(){
		if(user.equals(Login.get_User())){
			
		}else{}
	}
	
	public void addDoList(){
		
	}
	
	public boolean getCheck(){//체크 확인
		if(check==1){
			return true;
		}else{
			return false;
		}
	}
	
	public void saveData(){
		ArrayList<Data> datas = new ArrayList<Data>();
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try{
			fin = new FileInputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			ois = new ObjectInputStream(fin);
			
			ArrayList list = (ArrayList) ois.readObject();
			for(int i=0;i<list.size();i++){
				datas.add((Data) list.get(i));
			}
		}catch(Exception e){
			System.out.println();
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		}
		
	}
	
	public void loadData(){
		
	}
}
