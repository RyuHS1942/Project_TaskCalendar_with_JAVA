import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Date_Mo extends JFrame{
	JScrollPane MemoS;
	JTextArea MemoA;
	JTextField MemoTF;
	
	JButton AddB;
	JButton DelB;
	JButton ModB;
	JButton SchB;
	JPanel BLP;
	
	static ArrayList<Data> Data = new ArrayList<Data>();
	Calendal_Mo Calendal = new Calendal_Mo();
	Login_Mo Login = new Login_Mo();
	AddBut AddBut = new AddBut();
	DelBut DelBut = new DelBut();
	ModBut ModBut = new ModBut();
	SchBut SchBut = new SchBut();

	public void DateMo(){
		this.setTitle(Calendal.td+"/"+Calendal.tm+"/"+Calendal.td);
		this.setLayout(new BorderLayout());
		
		MemoTF = new JTextField(10);
		
		AddB = new JButton();
		AddB.setText("ADD");
		AddB.addActionListener(AddBut);
		
		DelB = new JButton();
		DelB.setText("DEL");
		DelB.addActionListener(DelBut);
		
		ModB = new JButton();
		ModB.setText("MOD");
		ModB.addActionListener(ModBut);
		
		SchB = new JButton();
		SchB.setText("SCH");
		SchB.addActionListener(SchBut);
		
		MemoA = new JTextArea(10,40);
		MemoA.setEnabled(false);
		MemoS = new JScrollPane();
		MemoS = new JScrollPane(MemoA);
		
		BLP = new JPanel();
		BLP.setLayout(new FlowLayout());
		BLP.add(MemoTF);
		BLP.add(AddB);
		BLP.add(DelB);
		BLP.add(ModB);
		BLP.add(SchB);
		
		this.add(BLP,BorderLayout.NORTH);
		this.add(MemoS,BorderLayout.CENTER);
		
		this.pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				saveData();
				dispose();
			}
		});
		this.setVisible(true);
	}
	
	public void addData(){//메모장으로 추가 시킬 것
		MemoA.append(MemoTF.getText()+"\n");
	}
	
	public void delData(){//데이터 자체를 삭제하기
		MemoA.setText("");
	}
	
	public void modData(){
		MemoA.setText(MemoTF.getText());
	}
	
	public void schData(){
		for(int i=0;i<Data.size();i++){
			if(MemoTF.getText().contains(Data.get(i).memo)){
				MemoA.append(Data.get(i).toString());
			}
		}
	}
	
	public void saveData(){
		int series = 0;
		int year,month,date,day;
		String user,memo;
		user = Login.user;
		year = Calendal.year;
		month = Calendal.month;
		date = Calendal.date;
		day = Calendal.dayorder;
		memo = MemoA.getText();
		
		System.out.println(series+"/"+user+"/"+year+"/"+month+"/"+date+"/"+day+"/"+memo);
		
		Data.add(new Data(series,user,year,month,date,memo));
		
		series++;
		
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try{
			fout = new FileOutputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(Data);
			oos.reset();
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		}
	}
	
	public void loadData(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try{
			fin = new FileInputStream("C:\\Users\\ryu\\Downloads\\data.txt");
			ois = new ObjectInputStream(fin);
			
			
			for(int i=0;i<Data.size();i++){
				if(Data.get(i).user.equals()){
					if(Data.get(i).year==Calendal.year){
						if(Data.get(i).month==Calendal.month){
							if(Data.get(i).date==Calendal.date){
								MemoA.setText(Data.get(i).memo);
							}
						}
					}
				}
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
	class AddBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addData();
		}
	}
	class DelBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			delData();
		}
	}
	class ModBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			modData();
		}
	}
	class SchBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			schData();
		}
	}
}
