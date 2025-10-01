
package pl_java.exercise_1.part_1;

import pl_java.exercise_1.part_0.QuestionType;

public class ParagraphAnswerQuestion {
    /*
    * Attributes
    */
    private String questionText;
    private String paragraphAnswer;
    private QuestionType questionType;


    /*
    * Constructors
    */
    public ParagraphAnswerQuestion(String questionText) {
        setQuestionType(QuestionType.PARAGRAPH_ANSWER);
        setQuestionText(questionText);
    }

    /*
    * Getters
    */
    public String getQuestionText() {
        return questionText;
    }

    public String getParagraphAnswer() {
        return paragraphAnswer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }


    /*
    * Setters
    */
    public void setParagraphAnswer(String paragraphAnswer) {
        // Perform Input Validations
        /*
        * 
        */
        this.paragraphAnswer = paragraphAnswer;
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