package pl_java.abstract_class.exercise_2.question;

import java.util.List;

public class MultipleChoiceQuestion extends Question{

/*
 * Attributes
 */
    private String multipleChoiceAnswer;

    private List<String> multipleChoiceOptions;


/*
 * Constructors
 */
    public MultipleChoiceQuestion(String questionText, List<String> choiceOptions) {
        setQuestionType(QuestionType.MULTIPLE_CHOICE);
        setQuestionText(questionText);
        setMultipleChoiceOptions(choiceOptions);
    }

/*
 * Getters
 */

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public List<String> getMultipleChoiceOptions() {
        return multipleChoiceOptions;
    }


/*
 * Setters
 */

    public void setMultipleChoiceAnswer(String multipleChoiceAnswer) {
        // Perform Input Validations
        /*
         * 
         */

        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    private void setMultipleChoiceOptions(List<String> multipleChoiceOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.multipleChoiceOptions = multipleChoiceOptions;
    }
}
