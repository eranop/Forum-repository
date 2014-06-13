package allcode;

import java.io.Serializable;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import java.util.Vector;

import services.report;

@Embeddable
public class Password implements Serializable{
	

	
	@Column(name="password_question1")
	static String question1 = "What is the name of your first pet";
	
	@Column(name="password_question2")
	static String question2 = "What is your favorate movie";
	
	@Column(name="password_question3")
	static String question3 = "What size is your underpants";
	
	@Transient
	public static Vector <String> questions = new Vector<String>();
	
	@Column(name="password")
	private String _pass;
	
	@Column(name="password_date")
	private Date _passwordDate;

	
	

	
	@Column(name="password_answer")
	private String _passAnswer;
	
	//private String _passQuestion;
	
	public Password(){
		_pass=null;
	}
	
	public Password (String password)
	{
		this.set_pass(password);
		_passwordDate = DateManagment.getDate();
	}
	
	public Password (String password, String question,String answer)
	{
		this.set_pass(password);
		this._passAnswer = answer;
		//this._passQuestion=question;
		_passwordDate = DateManagment.getDate();
	}
	
	public static void setQuestions()
	{
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
	}
	public static Vector<String> getQuestions(){
		return questions;
	}
	
	
	public report answerPasswordQuestion(String answer)
	{
		if (this._passAnswer.equals(answer))
			return report.OK;
		else
			return report.WRONG_PASSWORD_ANSWER;
	}	
	
	public Date get_passwordDate() {
		return _passwordDate;
	}
	public int daysSinceLastPassword() {
		return DateManagment.getDateDiffDays(_passwordDate, DateManagment.getDate());
	}

	public String get_passAnswer() {
		return _passAnswer;
	}
	public void set_passAnswer(String passAnswer) {
		this._passAnswer = passAnswer;
	}

	public String get_pass() {
		return _pass;
	}

	public void set_pass(String _pass) {
		this._pass = _pass;
	}
	
	

}
