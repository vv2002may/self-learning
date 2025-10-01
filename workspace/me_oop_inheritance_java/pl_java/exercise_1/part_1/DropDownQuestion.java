
package pl_java.exercise_1.part_1;

import pl_java.exercise_1.part_0.QuestionType;
import java.util.List;

public class DropDownQuestion {

    /*
    * Attributes
    */
    private String questionText;
    private String dropDownAnswer;
    private List<String> dropDownOptions;
    private QuestionType questionType;


    /*
    * Constructors
    */
    public DropDownQuestion(String questionText, List<String> choiceOptions) {
        setQuestionType(QuestionType.DROPDOWN);
        setQuestionText(questionText);
        setDropDownOptions(choiceOptions);
    }


    /*
    * Getters
    */
    public String getQuestionText() {
        return questionText;
    }

    public String getDropDownAnswer() {
        return dropDownAnswer;
    }

    public List<String> getDropDownOptions() {
        return dropDownOptions;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }


    /*
    * Setters
    */
    public void setDropDownAnswer(String dropDownAnswer) {
        // Perform Input Validations
        /*
        * 
        */
        this.dropDownAnswer = dropDownAnswer;
    }

    private void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    private void setDropDownOptions(List<String> dropDownOptions) {
        // Perform Input Validations
        /*
        * 
        */

        this.dropDownOptions = dropDownOptions;
    }

    private void setQuestionType(QuestionType questionType) {
        // Perform Input Validations
        /*
        * 
        */

        this.questionType = questionType;
    }  
}