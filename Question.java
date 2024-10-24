/***
 * The Question class represents an individual question in a quiz, including its text, answer options,
 * and correct answer. Each Question object has a unique ID that can be used to reference it in a question 
 * bank or quiz.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation: 21/10/2024
 */

public class Question {
    private final int id; 
    private String questionText; 
    private String[] options; 
    private String answer; 
    private String difficulty;

    /**
     * Constructor for initializing the question with its details.
     * 
     * @param id          The unique identifier for the question.
     * @param questionText The text of the question.
     * @param options      The possible options for the question.
     * @param answer       The correct answer.
     */
    public Question(int id, String questionText, String[] options, String answer, String difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.options = new String[options.length]; 
        for (int i = 0; i < options.length; i++) {
            this.options[i] = options[i]; 
        }
        this.answer = answer;
        this.difficulty = difficulty;
    }

    /**
     * Gets the unique identifier of the question.
     * 
     * @return The ID of the question.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the text of the question.
     * 
     * @return The question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gets the correct answer of the question.
     * 
     * @return The correct answer.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Gets the possible options for the question.
     * 
     * @return An array of options.
     */
    public String[] getOptions() {
        return options;
    }
    
    /***
     * Gets the difficulty of the question.
     * 
     * @return difficulty of the question
     */
 
	public String getDifficulty() {
		return  difficulty;
	}
}
