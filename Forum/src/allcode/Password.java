package allcode;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import services.report;

@Embeddable
public class Password implements Serializable{
	
	@Column(name="password")
	private String _pass;
	
	@Column(name="password_date")
	private Date _passwordDate;
	
	@Column(name="password_question")
	private String _passQuestion;
	
	@Column(name="password_answer")
	private String _passAnswer;
	
	public Password(){
		_pass=null;
	}
	
	public Password (String password)
	{
		this.set_pass(password);
		_passwordDate = DateManagment.getDate();
	}
	
	public Password (String password, String question, String answer)
	{
		this.set_pass(password);
		this._passQuestion = question;
		this._passAnswer = answer;
		_passwordDate = DateManagment.getDate();
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
	public String get_passQuestion() {
		return _passQuestion;
	}
	public void set_passQuestion(String passQuestion) {
		this._passQuestion = passQuestion;
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
