package pl_java.exercise_1.part_0;

import java.util.List;

public class Question {

/*
 * Attributes
 */
    private String questionText;
    private String shortAnswer;
    private String paragraphAnswer;
    private String multipleChoiceAnswer;
    private List<String> checkboxAnswerList;
    private String dropDownAnswer;

    private List<String> multipleChoiceOptions;
    private List<String> checkboxOptions;
    private List<String> dropDownOptions;

    private QuestionType questionType;


/*
 * Constructors
 */
    public Question(String questionText, QuestionType questionType) {
        setQuestionType(questionType);
        switch(questionType){
            case SHORT_ANSWER:
                setQuestionText(questionText);;
                break;
            case PARAGRAPH_ANSWER:
                setQuestionText(questionText);
                break;
            default:
                break;
        }
    }

    public Question(String questionText, List<String> choiceOptions, QuestionType questionType) {
        setQuestionType(questionType);
        setQuestionText(questionText);
        switch(questionType){
            case MULTIPLE_CHOICE:
                setMultipleChoiceOptions(choiceOptions);
                break;
            case CHECKBOX:
                setCheckboxOptions(choiceOptions);
                break;
            case DROPDOWN:
                setDropDownOptions(choiceOptions);
                break;
            default:
                break;
        }
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

    public String getParagraphAnswer() {
        return paragraphAnswer;
    }

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public List<String> getCheckboxAnswerList() {
        return checkboxAnswerList;
    }

    public String getDropDownAnswer() {
        return dropDownAnswer;
    }

    public List<String> getMultipleChoiceOptions() {
        return multipleChoiceOptions;
    }

    public List<String> getCheckboxOptions() {
        return checkboxOptions;
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
    public void setShortAnswer(String shortAnswer) {
        // Perform Input Validations
        /*
         * 
         */

        if(questionType == QuestionType.SHORT_ANSWER){
            this.shortAnswer = shortAnswer;
        }
    }

    public void setParagraphAnswer(String paragraphAnswer) {
        // Perform Input Validations
        /*
         * 
         */

        if(questionType == QuestionType.PARAGRAPH_ANSWER){
            this.paragraphAnswer = paragraphAnswer;
        }
    }

    public void setMultipleChoiceAnswer(String multipleChoiceAnswer) {
        // Perform Input Validations
        /*
         * 
         */

        if(questionType == QuestionType.MULTIPLE_CHOICE){
            this.multipleChoiceAnswer = multipleChoiceAnswer;
        }
    }

    public void setCheckboxAnswerList(List<String> checkboxAnswerList) {
        // Perform Input Validations
        /*
         * 
         */

        if(questionType == QuestionType.CHECKBOX){
            this.checkboxAnswerList = checkboxAnswerList;
        }
    }

    public void setDropDownAnswer(String dropDownAnswer) {
        // Perform Input Validations
        /*
         * 
         */

        if(questionType == QuestionType.DROPDOWN){
            this.dropDownAnswer = dropDownAnswer;
        }
    }

    private void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    private void setMultipleChoiceOptions(List<String> multipleChoiceOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.multipleChoiceOptions = multipleChoiceOptions;
    }

    private void setCheckboxOptions(List<String> checkboxOptions) {
        // Perform Input Validations
        /*
         * 
         */

        this.checkboxOptions = checkboxOptions;
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
