
package pl_java.exercise_1.part_2;

import pl_java.exercise_1.part_0.QuestionType;

public class Question {
    private String questionText;
    private QuestionType questionType;

    public String getQuestionText() {
        return questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    protected void setQuestionType(QuestionType questionType) {
        // Perform Input Validations
        /*
         * 
         */

        this.questionType = questionType;
    }

    protected void setQuestionText(String questionText) {
        // Perform Input Validations
        /*
         * 
         */
        
        this.questionText = questionText;
    }
}