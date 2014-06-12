package services;

import java.io.Serializable;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final report _report;
	private final Object _me;
	
	public Response(report report, Object it) {
		_report=report;
		_me=it;
	}
	public Response(report report) {
		_report=report;
		_me=null;
	}
	public report getReport(){
		return _report;
	}
	public Object getMe(){
		return _me;
	}
}
