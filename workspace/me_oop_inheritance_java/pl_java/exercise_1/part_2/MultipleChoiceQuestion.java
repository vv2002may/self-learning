
package pl_java.exercise_1.part_2;
import pl_java.exercise_1.part_0.QuestionType;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

/*
 * Attributes
 */
    // private String questionText;
    private String multipleChoiceAnswer;
    private List<String> multipleChoiceOptions;
    // private QuestionType questionType;


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
    // public String getQuestionText() {
    //     return questionText;
    // }

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public List<String> getMultipleChoiceOptions() {
        return multipleChoiceOptions;
    }

    // public QuestionType getQuestionType() {
    //     return questionType;
    // }


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

    // private void setQuestionText(String questionText) {
    //     this.questionText = questionText;
    // }

    private void setMultipleChoiceOptions(List<String> multipleChoiceOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.multipleChoiceOptions = multipleChoiceOptions;
    }

    // private void setQuestionType(QuestionType questionType) {
    //     // Perform Input Validations
    //     /*
    //      * 
    //      */

    //     this.questionType = questionType;
    // }
}