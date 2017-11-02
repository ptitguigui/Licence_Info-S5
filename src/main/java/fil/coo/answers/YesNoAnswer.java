package fil.coo.answers;

public class YesNoAnswer extends SingleAnswer {
	
	public YesNoAnswer(String answer) throws NullPointerException, InvalidAnswerException {
		super(answer, true);
	}

	@Override
	protected boolean isQuizTextValid(String quizText) {
		return checkUserAnswerIsValid(quizText);
	}

	public String getPrompt() {
		return "(oui/non) ";
	}

	protected boolean checkUserAnswerIsValid(String userAnswer) {
		return userAnswer.equals("oui") || userAnswer.equals("non");
	}
	
}
