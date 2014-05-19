package allcode;

import java.util.Date;

import services.report;

public class Password {
	
	private String _pass;
	private Date _passwordDate;
	private String _passQuestion;
	private String _passAnswer;
	
	public Password (String password)
	{
		this.set_pass(password);
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
