package pl_java.abstract_class.exercise_2.question;

public class ParagraphAnswerQuestion extends Question{
    /*
    * Attributes
    */
    private String paragraphAnswer;


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

    public String getParagraphAnswer() {
        return paragraphAnswer;
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
}
