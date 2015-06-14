import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calendal_Mo<todaydate> extends JFrame{
	int year,month,date,dayorder;//사용할 날자
	
	int startP;//첫번쨰 날자의 요일
	int endP;//마지막 날짜
	String[] day = {"SUN","MON","TUS","WED","THU","FRI","SAT"};//요일
	int Month[] = {31,28,31,30,31,30,31,31,30,31,30,31};//날자 수;
	int Date[][] = new int[6][day.length]; 
	
	JPanel CalendarP;
	JPanel UpP;
	JPanel DownP;
	
	JButton LeftMove;
	JButton Leftmove;
	JLabel YM;
	JButton RightMove;
	JButton Rightmove;
	
	JButton dayB;
	JButton dateB[][] = new JButton[6][day.length];
	
	Calendar cal = Calendar.getInstance();
	Move Move = new Move();
	DateMo DateMo = new DateMo();
	
	public Calendal_Mo(int year,int month,int date,int dayorder){
		this.year = year;
		this.month = month;
		this.date = date;
		this.dayorder = dayorder;
	}
	
	public int getyear(){
		return year;
	}
	
	public int getmonth(){
		return month;
	}
	
	public int getdate(){
		return date;
	}
	
	public int getdayorder(){
		return dayorder;
	}
	
	public void CalendarMo(){
		this.setTitle("Calendal");
		this.setSize(700, 400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		CalendarP = new JPanel();
		CalendarP.setSize(690,390);
		CalendarP.setLayout(new BorderLayout());
		this.add(CalendarP,BorderLayout.CENTER);
		
		setToday();
		
		UpP = new JPanel();
		UpP.setSize(680,50);
		CalendarP.add(UpP,BorderLayout.NORTH);
		UpP.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		
		LeftMove = new JButton("<<");
		LeftMove.addActionListener(Move);
		UpP.add(LeftMove);
				
		Leftmove = new JButton("<");
		Leftmove.addActionListener(Move);
		UpP.add(Leftmove);
		
		YM = new JLabel((year+1)+"  /  "+(month+1));
		YM.setSize(60, 0);
		UpP.add(YM);
		
		Rightmove = new JButton(">");
		Rightmove.addActionListener(Move);
		UpP.add(Rightmove);
		
		
		RightMove = new JButton(">>");
		RightMove.addActionListener(Move);
		UpP.add(RightMove);
		
		DownP = new JPanel();
		DownP.setSize(685,340);
		CalendarP.add(DownP,BorderLayout.CENTER);
		DownP.setLayout(new GridLayout(7,7));
		
		ShowCalendar();
				
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setToday(){
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		date = cal.get(Calendar.DATE);//오늘 날자
		dayorder = cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public void February(){//윤년 계산
		if(year%4==0&&year%100 !=0||year%400==0){
			 Month[1]++;
		}else{
			Month[1] = 28;
		}
	}
	//
	public int StartP(int dayorder){//날짜와 요일
		int time = 0;
		if(date>7){
			while(date>7){//첫째 주
				date = date-7;
			}
		}else if(date<0){
			while(0>date){
				date = date+7;
			}
		}
		while(date==1){
			date--;
			time++;
		}
		return dayorder-time;//1일의 시작 요일
	}
	
	public void EndP(){
		return;
	}
	
	public void InCalendar(){
		February();
		int num = 0;
		
		startP = StartP(startP);//첫번째만 두번째는 다른 방식으로
		//
		for(int i=0;i<6;i++){
			for(int j=0;j<day.length;j++){
				if(startP>0){
					Date[i][j] = 0;
					startP--;
				}else if(startP==0 && num<Month[month]+1){
					Date[i][j] = num++;
				}else{
					Date[i][j] = 0;
				}
			}
		}
	}
	
	private String toString(int i) {
		String j;
		j = i+"";
		return j;
	}
	
	public void ShowCalendar(){//달력의 스켈레톤
		InCalendar();
		
		for(int i=0;i<day.length;i++){
			dayB = new JButton(day[i]);//비활성화
			dayB.setEnabled(false);
			DownP.add(dayB);
			if(i==0){
				dayB.setBackground(Color.RED);
			}else if(i==6){
				dayB.setBackground(Color.BLUE);
			}
		}
		for(int i=0;i<6;i++){
			for(int j=0;j<day.length;j++){
				dateB[i][j]=new JButton();
				dateB[i][j].addActionListener(DateMo);
				if(Date[i][j]==0){
					dateB[i][j].setText("");
					dateB[i][j].setEnabled(false);
				}else{
					dateB[i][j].setText(toString(Date[i][j]));
				}
				DownP.add(dateB[i][j]);
			}
		}
	}
	
	public String dayordertoday(int dayorder){//요일을 문자로
		return day[dayorder];
	}
	
	public int DY(){
		int total=0;
		February();
		for(int  i=0;i<Month.length;i++){
			total += Month[i];
		}
		date -= total;
		return date%7;
	}
	
	public int DM(){
		February();
		date -= Month[month];
		return date%7;
	}
	
	public int  AY(){
		February();
		date += Month[month];
		return date&7;
	}
	
	public int AM(){
		int total=0;
		February();
		for(int  i=0;i<Month.length;i++){
			total += Month[i];
		}
		date += total;
		return date%7;
	}
	
	public void removeCalender(){
		for(int i=0;i<6*7;i++){
			DownP.removeAll();
		}
	}

	public void moveMonth(int mon){
		month += mon;
		if(month>11){
			while(month>11){
				year++;
				month -= 12;
			}
		}else if(month<0){
			while(month<0){
				year--;
				month += 12;
			}
		}
	}
	
	class Move implements ActionListener{
		public void actionPerformed(ActionEvent e){
			removeCalender();
			if(e.getSource()==LeftMove){
				moveMonth(-12);
				startP += DY();
				YM.setText((year+1)+"  /  "+(month+1));
			}else if(e.getSource()==Leftmove){
				moveMonth(-1);
				startP += DM();
				YM.setText((year+1)+"  /  "+(month+1));
			}else if(e.getSource()==Rightmove){
				moveMonth(+1);
				startP += AM();
				YM.setText((year+1)+"  /  "+(month+1));
			}else if(e.getSource()==RightMove){
				moveMonth(+12);
				startP += AY();
				YM.setText((year+1)+"  /  "+(month+1));
			}
			ShowCalendar();//전의 내용 초기화
		}
	}
	
	class DateMo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			boolean check = false;
			String DoList = null;
			String memo = null;
			
			Date_Mo Date = new Date_Mo(check, DoList, memo);
			Date.DateMo();
		}
	}
}

