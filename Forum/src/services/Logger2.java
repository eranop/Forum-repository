package services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Logger2 {
	private static PrintWriter writer;
	private static Logger2 _logUser;
	private static Logger2 _logSystem;
	
	private Logger2(String fileName) throws Exception, UnsupportedEncodingException {
		writer=new PrintWriter(fileName, "UTF-8");
	}
	public static void initLogUser(){
		if(_logUser==null){
			try{
				_logUser= new Logger2("userlog");
			}
			catch(Exception e){
				System.out.println("Error initializing userlog");
			}
		}
	}
	public static void initLogSystem(){
		if(_logUser==null){
			try{
				_logSystem= new Logger2("systemlog");
			}
			catch(Exception e){
				System.out.println("Error initializing systemlog");
			}
		}
	}
	public static Logger2 getLogUser(){
		return _logUser;
	}
	public static Logger2 getLogSystem(){
		return _logSystem;
	}
	//for bad reports
	public static void writeToLog(String function, report r){
		DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String report= reportSwitch(r);
		writer.write(date.toString() + "> "+ function + ": " + report);
		writer.flush();
	}
	// for good reports
	public static void writeToLog(String function){
		DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//String report= reportSwitch(r);
		writer.println(date.toString() + "> "+ function + ": " + "Done");
		writer.flush();
	}
	
	//convert reports to message for log
	private static String reportSwitch(report r){
		switch(r){
		case OK:
			return "Done";
		case NO_FORUM:
			return "forum not found";
		case NO_MEMBER:
			return "member not found";
		case NO_SUBFORUM:
			return "sub-forum not found";
		case NOT_LOGGED:
			return "user not logged";
		case NO_POST:
			return "post id not found";
		case ALREADY_MEMBER_EXIST:
			return "member is already exist";
		case ALREADY_EMAIL_EXIST:
			return "email is already exist";
		case ALREADY_FORUM_EXIST:
			return "forum is already exist";
		case ALREADY_SUBFORUM_EXIST:
			return "the sub forum is already exist";
		case MEMBER_ALREADY_IN_FORUM:
			return "member is already in forum";
		case IS_NOT_ADMIN:
			return "not allowed- is not administrator";
		case IS_NOT_MODERATOR:
			return "not allowed- is not moderator";
		case IS_NOT_SUPERADMIN:
			return "not allowed- is not super-administrator";
		case NO_SUCH_SUBFORUM:
			return "the sub-forum not found";
		case NO_SUCH_USER_NAME:
			return "this user name not found";
		case NO_SUCH_FORUM:
			return "the furum not found";
		case NO_SUCH_POST:
			return "post id not found";
		case NOT_POST_OWNER:
			return "not allowed- not post owner";
		case INVALID_PASSWORD:
			return "invalid password";
		case PASSWORD_ALREADY_BEEN_USED:
			return "use password whotch already used";
		case WRONG_PASSWORD_ANSWER:
			return "wrog password answer";
		case NULL_ARGUMENTS:
			return "get null arguments";
		case NOT_ALLOWED:
			return "not allowed";
		case DENIED_BY_POLICY:
			return "denied by policy";
		case FAIL:
			return "failed";
		default:
			return "report " + r.toString() +" is not supportted yet";
		}
	}
	
	
	

}
