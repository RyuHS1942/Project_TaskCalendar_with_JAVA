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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
	
	AddBut AddBut = new AddBut();
	DelBut DelBut = new DelBut();
	ModBut ModBut = new ModBut();
	SchBut SchBut = new SchBut();
	
	Calendal_Mo CalendalMo;
	ArrayList<Data> Data;

	public void DateMo(Calendal_Mo CalendalMo,ArrayList<Data> Data){
		this.setLayout(new BorderLayout());
		this.CalendalMo = CalendalMo;
		this.Data = Data;
		
		this.setTitle(Login_Mo.user+"/"+CalendalMo.ty+"/"+CalendalMo.tm+"/"+CalendalMo.Date[CalendalMo.datei][CalendalMo.datej]);
		
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
			public void windowOpened(WindowEvent e){
				loadData();
			}
		});
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
			if(MemoTF.getText().contains(Data.get(i).getmemo())){
				MemoA.append(Data.get(i).toString());
			}
		}
	}
	
	public void saveData(){
		String user2 = Login_Mo.user;
		int year2 = CalendalMo.ty;
		int month2 = CalendalMo.tm;
		int date2 = CalendalMo.Date[CalendalMo.datei][CalendalMo.datej];
		String memo2 = MemoA.getText();
		
		Data.add(new Data(user2,year2,month2,date2,memo2));
		
		FileWriter fw = null;
		try{
			fw = new FileWriter("C:\\Users\\ryu\\Downloads\\data.txt");
			for(int i=0;i<Data.size();i++){
				fw.write(Data.get(i).getuser()+"^^"+Data.get(i).getyear()+"^^"+Data.get(i).getmonth()
						+"^^"+Data.get(i).getdate()+"^^"+Data.get(i).getmemo()+"\n");
				System.out.println(Data.get(i).toString());
				}
		}catch(Exception ex){
		}finally{
			try{
				fw.close();
			}catch(IOException e){}
		}
	}
	
	public void loadData(){
		BufferedReader br = null;
		try{
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:\\Users\\ryu\\Downloads\\data.txt"));
			while((sCurrentLine = br.readLine()) != null){
				String[] info = sCurrentLine.split("^^");
				for(int i=0;i<Data.size();i++){
					if(info[0].equals(Login_Mo.user)){
						if(info[1].equals(CalendalMo.ty)){
							if(info[2].equals(CalendalMo.tm)){
								if(info[3].equals(CalendalMo.Date[CalendalMo.datei][CalendalMo.datej])){
									MemoA.append(Data.get(i).getmemo());
								}
							}
						}
					}
				}
			}
		}catch(Exception e){}
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
