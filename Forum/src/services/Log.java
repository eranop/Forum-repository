package services;


public class Log {
	private report LogReport;
	private String LogMessage;
	
	public Log(report LogReport, String LogMessage){
		this.LogReport=LogReport;
		this.LogMessage=LogMessage;
	}
	
	public report GetReport(){
		return this.LogReport;
	}
	public String GetMessage(){
		return this.LogMessage;
	}
	public void SetReport(report set){
		this.LogReport=set;
	}
	public void SetMessage(String set){
		this.LogMessage=set;
	}
}
