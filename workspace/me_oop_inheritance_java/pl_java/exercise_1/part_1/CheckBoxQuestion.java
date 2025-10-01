
package pl_java.exercise_1.part_1;

import pl_java.exercise_1.part_0.QuestionType;
import java.util.List;

public class CheckBoxQuestion {

/*
 * Attributes
 */
    private String questionText;
    private List<String> checkboxAnswerList;

    private List<String> checkboxOptions;

    private QuestionType questionType;


/*
 * Constructors
 */
    public CheckBoxQuestion(String questionText, List<String> choiceOptions) {
        setQuestionType(QuestionType.CHECKBOX);
        setQuestionText(questionText);
        setCheckboxOptions(choiceOptions);
    }


/*
 * Getters
 */
    public String getQuestionText() {
        return questionText;
    }

    public List<String> getCheckboxAnswerList() {
        return checkboxAnswerList;
    }

    public List<String> getCheckboxOptions() {
        return checkboxOptions;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }


/*
 * Setters
 */

    public void setCheckboxAnswerList(List<String> checkboxAnswerList) {
        // Perform Input Validations
        /*
         * 
         */

        this.checkboxAnswerList = checkboxAnswerList;
    }

    private void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    private void setCheckboxOptions(List<String> checkboxOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.checkboxOptions = checkboxOptions;
    }

    private void setQuestionType(QuestionType questionType) {
        // Perform Input Validations
        /*
         * 
         */

        this.questionType = questionType;
    }
}