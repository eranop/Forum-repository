package allcode;

import java.io.Serializable;
import java.util.Date;
<<<<<<< HEAD

import javax.persistence.Column;
import javax.persistence.Embeddable;
=======
import java.util.Vector;
>>>>>>> refs/heads/version3.6

import services.report;

@Embeddable
public class Password implements Serializable{
	
<<<<<<< HEAD
	@Column(name="password")
=======
	static String question1 = "What is the name of your first pet";
	static String question2 = "What is your favorate movie";
	static String question3 = "What size is your underpants";
	
	public static Vector <String> questions;
	
>>>>>>> refs/heads/version3.6
	private String _pass;
	
	@Column(name="password_date")
	private Date _passwordDate;
<<<<<<< HEAD
	
	@Column(name="password_question")
	private String _passQuestion;
	
	@Column(name="password_answer")
=======
>>>>>>> refs/heads/version3.6
	private String _passAnswer;
	
	public Password(){
		_pass=null;
	}
	
	public Password (String password)
	{
		this.set_pass(password);
		_passwordDate = DateManagment.getDate();
	}
	
	public Password (String password, String answer)
	{
		this.set_pass(password);
		this._passAnswer = answer;
		_passwordDate = DateManagment.getDate();
	}
	
	public void setQuestions()
	{
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
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
