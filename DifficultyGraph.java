/**
 * The DifficultyGraph class generates a graphical representation
 * of question difficulty levels based on their IDs.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 24/20/2024
 */
public class DifficultyGraph {
    private int[] questionIds; 
    private String[] difficultyLevels; 

    /**
     * Constructor to initialize question IDs and their corresponding difficulty levels.
     *
     * @param questionIds     An array of integers representing the IDs of the questions.
     * @param difficultyLevels An array of strings representing the difficulty levels for each question.
     */
    public DifficultyGraph(int[] questionIds, String[] difficultyLevels) {
        this.questionIds = new int[questionIds.length]; 
        this.difficultyLevels = new String[difficultyLevels.length]; 
        
        for (int i = 0; i < questionIds.length; i++) {
            this.questionIds[i] = questionIds[i];
            this.difficultyLevels[i] = difficultyLevels[i];
        }
    }

    /**
     * Displays the difficulty graph in the console.
     * Each question's difficulty level is represented by a line of asterisks,
     * where the number of asterisks corresponds to the difficulty score.
     */
    public void displayGraph() {
        System.out.println(Constant.DIFFICULTY_LEVEL);

        for (int i = 0; i < questionIds.length; i++) {
            StringBuilder graphLine = new StringBuilder(Constant.Q + questionIds[i] + Constant.COLON);
            
            int difficultyScore = getDifficultyScore(difficultyLevels[i]);

            for (int j = 0; j < difficultyScore; j++) {
                graphLine.append("*"); 
            }

            System.out.println(graphLine.toString()); 
        }
    }

    /**
     * Gets the difficulty score based on the provided difficulty level.
     * 
     * @param difficulty A string representing the difficulty level of a question.
     * @return An integer score representing the difficulty:
     *         1 for "Easy", 2 for "Medium", 3 for "Hard", and 0 for unknown levels.
     */
    private int getDifficultyScore(String difficulty) {
        char[] easy = {'E', 'A', 'S', 'Y'};
        char[] medium = {'M', 'E', 'D', 'I', 'U', 'M'};
        char[] hard = {'H', 'A', 'R', 'D'};

        if (difficulty.length() == easy.length) {
            boolean isEasy = true;
            for (int i = 0; i < easy.length; i++) {
                if (difficulty.charAt(i) != easy[i] && difficulty.charAt(i) != (easy[i] + 32)) {
                    isEasy = false;
                    break;
                }
            }
            if (isEasy) return 1; 
        }

        if (difficulty.length() == medium.length) {
            boolean isMedium = true;
            for (int i = 0; i < medium.length; i++) {
                if (difficulty.charAt(i) != medium[i] && difficulty.charAt(i) != (medium[i] + 32)) {
                    isMedium = false;
                    break;
                }
            }
            if (isMedium) return 2; 
        }

        if (difficulty.length() == hard.length) {
            boolean isHard = true;
            for (int i = 0; i < hard.length; i++) {
                if (difficulty.charAt(i) != hard[i] && difficulty.charAt(i) != (hard[i] + 32)) {
                    isHard = false;
                    break;
                }
            }
            if (isHard) return 3; 
        }

        return 0; 
    }
   
    // This is the main method
    public static void main(String[] args) {
         int[] questionIds = {101, 102, 103, 104, 105, 106, 107, 108};
         String[] difficultyLevels = {"Easy", "", "Hard", "Easy", "Medium", "Easy", "ard", "medium"};

         DifficultyGraph graph = new DifficultyGraph(questionIds, difficultyLevels);
         graph.displayGraph(); 
        }
}
