package fil.coo.answerHandler;

import fil.coo.answers.Answer;
import fil.coo.answers.InvalidAnswerException;
import fil.coo.answers.TextAnswer;
import org.apache.log4j.Logger;

public class TextAnswerHandler extends AnswerHandler {

    private static final Logger logger = Logger.getLogger(YesNoAnswerHandler.class.getSimpleName());

    @Override
    public Answer createAnswer(String answerText) {
        try {
            return new TextAnswer(answerText, true);
        } catch (InvalidAnswerException e) {
            logger.debug("Failed to create " + this.getClass().getSimpleName() + ", will try " + next.getClass()
                    .getSimpleName());
            return this.next.createAnswer(answerText);
        }
    }
}
