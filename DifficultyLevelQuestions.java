public class DifficultyLevelQuestions {
    private Question[] questions; // Array to hold questions

    // Constructor
    public DifficultyLevelQuestions(Question[] questions) {
        this.questions = questions; // Initialize with the quiz questions
    }

    /***
     * this function displays the difficulty level of question in quiz
     * 
     * @param level takes the difficulty level of quiz as argument
     */
    public void displayQuestionsByDifficulty(String level) {
        System.out.println("Questions for difficulty level: " + level);
        for (int i = 0; i < questions.length; i++) {
            if (questions[i] != null && questions[i].getDifficulty().equalsIgnoreCase(level)) {
                System.out.println("Question " + questions[i].getId() + ": " + questions[i].getQuestionText());
            }
        }
    }
}
