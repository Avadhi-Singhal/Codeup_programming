/**
 * The DefaultQuiz class represents a default quiz with pre-defined questions.
 * 
 * Creator: Uddeshya Patidar
 * 
 * Date of Creation: 08/10/2024
 */
public class DefaultQuiz {

    private String title; 
    private Question[] questions;
    private int questionCount; 

    //Constructor for the DefaultQuiz class, initializing the quiz with a title and predefined questions.
    public DefaultQuiz() {
        this.title = "Basic Java Quiz"; 
        this.questions = new Question[Message.DEFAULT_QUESTIONS.length]; 
        this.questionCount = 0; 
        initializeDefaultQuestions();
    }

    //It creates Question objects and stores them in the questions array.
    private void initializeDefaultQuestions() {
        String[] questionsData = Message.DEFAULT_QUESTIONS; 
        String[][] optionsData = Message.DEFAULT_OPTIONS; 
        String[] answersData = Message.DEFAULT_ANSWERS; 
        
        // Iterate through the default question data and add them to the questions array
        for (int i = 0; i < questionsData.length; i++) {
            questions[questionCount++] = new Question(i + 1, questionsData[i], optionsData[i], answersData[i], title);
        }
    }

    /**
     * Gets a question by index.
     * 
     * @return The Question object at the specified index, or null if index is out of bounds.
     */
    public Question getQuestion(int index) {
        if (index >= 0 && index < questionCount) {
            return questions[index]; 
        }
        return null; 
    }

    /**
     * Gets the total number of questions.
     * 
     * @return The total number of questions in the quiz.
     */
    public int getQuestionCount() {
        return questionCount; // Return the count of questions
    }

    /**
     * Gets the title of the quiz.
     * 
     * @return The title of the quiz.
     */
    public String getTitle() {
        return title; // Return the title of the quiz
    }
}
