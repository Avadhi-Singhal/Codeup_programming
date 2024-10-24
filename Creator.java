import java.util.Scanner;

/***
 * The Creator class facilitates the creation of quizzes and the addition of questions to a QuestionBank.
 *
 * Owner : Avadhi-Singhal
 *
 * Date of Creation : 09/10/2024
 */
public class Creator extends User {

    Utility utility = new Utility();
    Constant constant = new Constant();
    private Quiz quiz;
    private QuestionBank questionBank;

    /**
     * Constructor for initializing the Creator with a QuestionBank.
     *
     * @param questionBank The question bank to be managed by the Creator.
     */
    public Creator(String name, QuestionBank questionBank) {
        super(name);
        this.questionBank = questionBank;
    }

    /**
     * Starts the process of creating quizzes and adding questions.
     */
    public void create() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {
            utility.displayMenu();
            int choice = utility.getValidChoice(scanner);

            switch (choice) {
                case 1:
                    String title = utility.getValidTitle(scanner);
                    this.quiz = new Quiz(title);
                    createQuiz(scanner, quiz);
                    break;

                case 2:
                    addQuestions(scanner);
                    break;

                case 3:
                    continueLoop = false;
                    System.out.println(constant.EXIT_MESSAGE);
                    break;

                default:
                    System.out.println(constant.INVALID_CHOICE);
                    break;
            }
        }
    }

    /**
     * Creates a quiz by allowing the user to select questions from the question bank.
     *
     * @param scanner The scanner to read user input.
     * @param quiz    The quiz to be created.
     */
    private void createQuiz(Scanner scanner, Quiz quiz) {
        questionBank.displayQuestions();

        if (questionBank.getQuestionCount() == 0) {
            System.out.println(constant.EMPTY_QUESTION_BANK_MSG);
            return;
        }

        boolean addingQuestions = true;
        while (addingQuestions) {
            System.out.println(constant.ADD_QUESTION_BY_ID);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(constant.DONE)) {
                addingQuestions = false;
                break;
            }

            addQuestionToQuiz(scanner, input, quiz);
        }
        quiz.displayQuiz();
    }

    /**
     * Adds a selected question from the question bank to the quiz.
     *
     * @param scanner The scanner to read user input.
     * @param input   The user input for question ID.
     * @param quiz    The quiz to add the question to.
     */
    private void addQuestionToQuiz(Scanner scanner, String input, Quiz quiz) {
        try {
            int questionId = Integer.parseInt(input);
            Question question = questionBank.getQuestionById(questionId);
            if (question != null) {
                System.out.println(constant.ADD_QUESTION + question.getQuestionText());
                quiz.addQuestion(question);
            } else {
                System.out.println(constant.INVALID_QUESTION_ID);
            }
        } catch (NumberFormatException e) {
            System.out.println(constant.PROMPT_VALID_INTEGER);
        }
    }

    /**
     * Allows the user to add new questions to the question bank.
     *
     * @param scanner The scanner to read user input.
     */
    private void addQuestions(Scanner scanner) {
        System.out.println(constant.MULTIPLE_QUESTION_MESSAGE);

        boolean addingQuestions = true;
        while (addingQuestions) {
            String questionText = utility.getQuestionText(scanner);
            if (questionText.equalsIgnoreCase(constant.DONE)) {
                addingQuestions = false;
                break;
            }

            String[] options = getValidOptions(scanner);
            String answer = getAnswer(scanner, options);
            questionBank.addQuestion(questionText, options, answer);
        }
    }

    /**
     * Gets the options for a question from the user.
     *
     * @param scanner The scanner to read user input.
     * @return An array of options for the question.
     */
    private String[] getValidOptions(Scanner scanner) {
        String[] options = new String[constant.DEFAULT_OPTIONS_SIZE];
        int optionCount = 0;

        while (optionCount < constant.DEFAULT_OPTIONS_SIZE) {
            System.out.println(constant.ENTER_OPTION + (optionCount + 1) + constant.CONSTRAINT_MSG);
            String option = scanner.nextLine();

            if (option.equalsIgnoreCase(constant.DONE)) {
                break;
            }

            if (option.trim().isEmpty() || option.length() > 100) {
                System.out.println(constant.EMPTY_OPTION_MSG);
                continue;
            }

            if (isDuplicateOption(option, options, optionCount)) {
                System.out.println(constant.DUPLICATE_OPTION_MSG);
                continue;
            }

            options[optionCount++] = option;
        }
        String[] finalOptions = new String[optionCount];
        System.arraycopy(options, 0, finalOptions, 0, optionCount);
        return finalOptions;
    }

    /**
     * Checks for duplicate options.
     *
     * @param option       The option to check for duplicates.
     * @param options      The array of existing options.
     * @param currentCount The current count of options.
     * @return True if duplicate found, false otherwise.
     */
    private boolean isDuplicateOption(String option, String[] options, int currentCount) {
        for (int i = 0; i < currentCount; i++) {
            if (options[i].equals(option)) {
                return true; // Found a duplicate
            }
        }
        return false; // No duplicate found
    }

    /**
     * Gets the correct answer for a question from the user.
     *
     * @param scanner The scanner to read user input.
     * @param options The possible options for the question.
     * @return The correct answer.
     */
    private String getAnswer(Scanner scanner, String[] options) {
        String answer = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter the correct answer (must be one of the options):");
            answer = scanner.nextLine().trim();
            String[] lowerCaseOptions = new String[options.length];
            for (int i = 0; i < options.length; i++) {
                lowerCaseOptions[i] = options[i].toLowerCase();
            }

            if (isOptionValid(answer, lowerCaseOptions)) {
                valid = true;
            } else {
                System.out.println("Invalid answer. Please enter one of the provided options.");
            }
        }
        return answer;
    }

    /**
     * Checks if the answer is in the provided options.
     *
     * @param answer  The answer to validate.
     * @param options The array of available options.
     * @return True if the answer is valid, false otherwise.
     */
    private boolean isOptionValid(String answer, String[] options) {
        String lowerCaseAnswer = answer.toLowerCase();
        for (String option : options) {
            if (lowerCaseAnswer.equals(option.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}