package pl_java.abstract_class.exercise_2.question;

import java.util.List;

public class CheckBoxQuestion extends Question{

/*
 * Attributes
 */
    private List<String> checkboxAnswerList;
    private List<String> checkboxOptions;


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

    public List<String> getCheckboxAnswerList() {
        return checkboxAnswerList;
    }

    public List<String> getCheckboxOptions() {
        return checkboxOptions;
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

    private void setCheckboxOptions(List<String> checkboxOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.checkboxOptions = checkboxOptions;
    }
}