import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calendal_Mo extends JFrame{
	int year, month, date;
	String day;//ø‰¿œ
	String user;
	
	JPanel CalendarP;
	JLabel CalendarDay;
	JButton Calendardate;
	
	Calendar cal = Calendar.getInstance();
	
	public Calendal_Mo(){
		this.setTitle("Calendal");
		this.setSize(700, 400);
		this.setResizable(false);
		this.setLayout(new GridLayout(7,6));
		
		CalendarDay = new JLabel();
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setToday(){
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		date = cal.get(Calendar.DATE);
	}
}

