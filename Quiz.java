/***
 * The Quiz class represents a quiz with a title and a collection of questions.
 *
 * Owner : Avadhi-Singhal
 *
 * Date of Creation : 10/10/2024
 */

public class Quiz {
    Constant constant = new Constant();
    private String title;
    private Question[] questions;
    private int currentQuestionCount;

    /**
     * Constructor for initializing the quiz with a title.
     *
     * @param title The title of the quiz.
     */
    public Quiz(String title) {
        this.title = title;
        this.questions = new Question[constant.DEFAULT_QUESTION_NUMBERS];
        this.currentQuestionCount = 0;
    }

    /**
     * Displays the quiz details, including the title and questions.
     */
    public void displayQuiz() {
        System.out.println(constant.QUIZ_TITLE + title);
        for (int i = 0; i < currentQuestionCount; i++) {
            System.out.println(constant.ID + questions[i].getId() + constant.HYPHEN + questions[i].getQuestionText());
        }
    }

    /**
     * Adds a question to the quiz.
     *
     * @param question The question to be added.
     */
    public void addQuestion(Question question) {
        if (currentQuestionCount >= questions.length) {
            resizeQuiz();
        }
        questions[currentQuestionCount++] = question;
    }

    /**
     * Resizes the question array to accommodate more questions.
     */
    private void resizeQuiz() {
        Question[] newQuestions = new Question[questions.length * 2];
        for (int i = 0; i < questions.length; i++) {
            newQuestions[i] = questions[i];
        }
        questions = newQuestions;
    }

    public String getTitle() {
        return title;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public int getCurrentQuestionCount() {
        return currentQuestionCount;
    }

	public Question getQuestion(int i) {
		return null;
	}
}
