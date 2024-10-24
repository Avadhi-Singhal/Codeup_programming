/***
 * The QuestionBank class is responsible for managing a collection of Question objects. It allows adding new questions, 
 * retrieving questions by their ID, and displaying all stored questions. The class dynamically resizes its internal 
 * array to accommodate more questions when needed.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 10/10/2024
 */

public class QuestionBank {
	Constant constant = new Constant(); 
	
    private Question[] questions; 
    private int questionCount; 
    public String difficulty = null;

    /**
     * Constructor for initializing the question bank with a default size.
     */
    public QuestionBank() {
        this.questions = new Question[constant.DEFAULT_QUESTION_NUMBERS]; 
        this.questionCount = 0;
    }

    /**
     * Adds a new question to the question bank, resizing if necessary.
     * 
     * @param questionText The text of the question to add.
     * @param options      The possible options for the question.
     * @param answer       The correct answer.
     */
    public void addQuestion(String questionText, String[] options, String answer) {
        if (questionCount >= questions.length) {
            resizeQuestionBank(); 
        }
		questions[questionCount++] = new Question(questionCount, questionText, options, answer, difficulty); 
        System.out.println(constant.QUESTION_ADDED_MSG  + questionCount);
    }

    /**
     * Retrieves a question by its unique identifier.
     * 
     * @param id The ID of the question to retrieve.
     * @return The question if found, otherwise null.
     */
    public Question getQuestionById(int id) {
        for (int i = 0; i < questionCount; i++) {
            if (questions[i].getId() == id) {
                return questions[i]; 
            }
        }
        return null; 
    }

    /**
     * Gets the current count of questions in the question bank.
     * 
     * @return The current question count.
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * Displays all questions in the question bank.
     */
    public void displayQuestions() {
        if (questionCount == 0) {
            System.out.println(constant.NO_QUESTION_AVAILABLE);
        } else {
            for (int i = 0; i < questionCount; i++) {
                System.out.println(constant.ID + questions[i].getId() + constant.HYPHEN + questions[i].getQuestionText());
            }
        }
    }

    /**
     * Resizes the question bank array when needed.
     */
    private void resizeQuestionBank() {
        Question[] newQuestions = new Question[questions.length * 2]; 
        for (int i = 0; i < questions.length; i++) {
            newQuestions[i] = questions[i]; 
        }
        questions = newQuestions; 
    }
}