
package pl_java.exercise_1.part_1;

import pl_java.exercise_1.part_0.QuestionType;

public class ShortAnswerQuestion {
    /*
    * Attributes
    */
    private String questionText;
    private String shortAnswer;
    private QuestionType questionType;


    /*
    * Constructors
    */
    public ShortAnswerQuestion(String questionText) {
        setQuestionType(QuestionType.SHORT_ANSWER);
        setQuestionText(questionText);
    }

    /*
    * Getters
    */
    public String getQuestionText() {
        return questionText;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }


    /*
    * Setters
    */
    public void setShortAnswer(String shortAnswer) {
        // Perform Input Validations
        /*
        * 
        */
        this.shortAnswer = shortAnswer;
    }

    private void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    private void setQuestionType(QuestionType questionType) {
        // Perform Input Validations
        /*
        * 
        */

        this.questionType = questionType;
    }
}