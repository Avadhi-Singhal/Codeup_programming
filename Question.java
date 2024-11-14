/***
 * The Question class models a quiz question with text, options, and a correct answer.
 * It provides methods to retrieve question details and validate answers.
 * Owner : Avadhi-Singhal
 * Date of Creation : 14/11/2024
 */
 
class Question {
    private String questionText;
    private String[] options;
    private char correctAnswer;

    /**
     * Constructs a Question with given text, options, and correct answer.
     * @param questionText the question to display
     * @param options the multiple-choice options for this question
     * @param correctAnswer the correct answer as a character (e.g., 'A', 'B')
     */
    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Retrieves the question text.
     * @return the text of the question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Retrieves the answer options.
     * @return an array of option strings
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Retrieves the correct answer.
     * @return the correct answer as a character
     */
    public char getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Checks if a given answer is correct.
     * @param answer the user's answer to check
     * @return true if the answer is correct, false otherwise
     */
    public boolean isCorrectAnswer(char answer) {
        return answer == correctAnswer;
    }
}

