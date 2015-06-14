
public class Data {
	
	static int year, month, date, day;
	static String user;
	static String memo;
	
	public Data(int year,int month,int date,int day,
			String user,String memo){
		this.year = year;
		this.month = month;
		this.date = date;
		this.day = day;
		this.user = user;
		
		this.memo = memo;
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
	
	public int getday(){
		return day;
	}
	
	public String getuser(){
		return user;
	}
	
	public String getmemo(){
		return memo;
	}
	
	public void setyear(int year){
		this.year = year;
	}
	
	public void setmonth(int month){
		this.month = month;
	}
	
	public void setdate(int date){
		this.date = date;
	}

	public void setday(int day){
		this.day = day;
	}
	
	public void setuser(String user){
		this.user =user;
	}
	
	public void setmemo(String memo){
		this.memo = memo;
	}
	
	public String toString(){
		return getyear()+","+getmonth()+","+getdate()+","+getday()+","
	+getuser()+","+getmemo();
	}
}
