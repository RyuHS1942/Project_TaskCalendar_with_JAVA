
public class Data {
	
	static int series,year, month, date, day;
	static String user;
	static String memo;
	
	public Data(int series,String user,int year,int month,int date,String memo){
		this.series = series;
		this.year = year;
		this.month = month;
		this.date = date;
		this.user = user;
		this.memo = memo;
	}
	
	public int getseries(){
		return series;
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
	
	public void setseries(int series){
		this.series = series;
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
	
	public String getYYMMDD(){
		return getyear()+"/"+getmonth()+"/"+getdate();
	}
	
	public String toString(){
		return getseries()+","+getyear()+","+getmonth()+","+getdate()+","+getday()+","
	+getuser()+","+getmemo();
	}
}
