import java.util.Scanner;

public class Participant extends User {
    private Scanner input;
    private int score;

    // Constructor
    public Participant(String name) {
        super(name);
        this.input = new Scanner(System.in);
    }

    // Allows participants to take a quiz and stores their score
    public void takeQuiz(Quiz createdQuiz) {
        System.out.println("Welcome to the quiz, " + this.getName());
        
        // Ask user to choose between default and created quiz
        System.out.println("Choose a quiz:");
        System.out.println("1. Default Quiz");
        System.out.println("2. Created Quiz");
        String choice = input.nextLine();

        if (choice.equals("1")) {
        	System.out.println("No default quiz to show chose another");
        } else if (choice.equals("2")) {
            if (createdQuiz != null) {
                String[] userAnswers = attempt(createdQuiz);
                score = calculateScore(userAnswers, createdQuiz);
                System.out.println("Your score for the Created Quiz is: " + score);
            } else {
                System.out.println("No created quiz available.");
            }
        } else {
            System.out.println("Invalid choice. No quiz taken.");
        }
        showQuestionsByDifficulty(createdQuiz);
    }

    
 // New method to show questions by difficulty
    private void showQuestionsByDifficulty(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the difficulty level (easy, medium, hard): ");
        String level = scanner.nextLine();

        DifficultyLevelQuestions difficultyQuestions = new DifficultyLevelQuestions(quiz.getQuestions());
        difficultyQuestions.displayQuestionsByDifficulty(level);
    }


// Attempting questions in a quiz
    public String[] attempt(Quiz quiz) {
        int questionCount = quiz.getCurrentQuestionCount();
        String[] userAnswers = new String[questionCount];
        int index = 0;
        while (index < questionCount) {
            displayQuestion(quiz, index);
            String choice = input.nextLine();
            index = handleUserChoice(choice, index, userAnswers);
        }
        System.out.println("Quiz finished!");
        return userAnswers;
    }


    // Displays questions with options
    public void displayQuestion(Quiz quiz, int index) {
        Question question = quiz.getQuestions()[index];
        System.out.println("Question " + (index + 1) + ": " + question.getQuestionText());

        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
        System.out.println("Enter 'n' for next question, 'p' for previous question, or a number to answer:");
    }

    // Handles user input for navigation and answering
    private int handleUserChoice(String choice, int index, String[] userAnswers) {
        switch (choice) {
            case "1":
            case "2":
            case "3":
            case "4":
                userAnswers[index] = choice;
                return index + 1;

            case "p":
                return Math.max(index - 1, 0); // Prevent going below index 0

            case "n":
                return Math.min(index + 1, userAnswers.length - 1); // Prevent exceeding bounds

            case "s":
                System.out.println("Skipping the quiz.");
                return index;

            default:
                System.out.println("Invalid input. Please try again.");
                return index;
        }
    }

    // Calculates and returns the score for DefaultQuiz
    private int calculateScore(String[] userAnswers, DefaultQuiz quiz) {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            Question question = quiz.getQuestion(i);
            if (question != null) {
                String correctAnswer = question.getAnswer();
                String[] options = question.getOptions();
                if (userAnswers[i] != null && correctAnswer.equals(options[Integer.parseInt(userAnswers[i]) - 1])) {
                    score += 1; // Increment score for correct answers
                }
            }
        }
        return score; // Return the total score
    }

    // Calculates and returns the score for created quiz
    private int calculateScore(String[] userAnswers, Quiz quiz) {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            Question question = quiz.getQuestions()[i];
            if (question != null) {
                String correctAnswer = question.getAnswer();
                String[] options = question.getOptions();
                if (userAnswers[i] != null && correctAnswer.equals(options[Integer.parseInt(userAnswers[i]) - 1])) {
                    score += 1; // Increment score for correct answers
                }
            }
        }
        return score; // Return the total score
    }

	public int getScore() {
		return score;
	}
}
