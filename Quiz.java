/**
 * The Quiz class manages quiz questions, user responses, time tracking, and result display 
 * with multi-threaded timer functionality.
 * Owner : Avadhi-Singhal
 * Date of creation : 14/11/2024 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The TimerThread class handles the countdown timer for each quiz question, 
 * ensuring that the quiz times out after a specific duration.
 */
class TimerThread extends Thread {
    private final long duration;
    private final Quiz quiz;

    /**
     * Constructs a new TimerThread for timing out the quiz after a given duration.
     * @param duration the time in milliseconds for the countdown
     * @param quiz the quiz instance to set the timed-out status
     */
    public TimerThread(long duration, Quiz quiz) {
        this.duration = duration;
        this.quiz = quiz;
    }

    /**
     * Runs the countdown timer. If the time expires, the quiz is marked as timed out.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            quiz.setTimedOut(true);
            System.out.println(Constant.TIMESUP_PROMPT);
        } catch (InterruptedException e) {
            // If interrupted, the timer stops (i.e., user answered within time).
        }
    }
}

class Quiz {
    private List<Question> questions;
    private List<String> responses;
    private List<Boolean> correctness;
    private List<Long> timeTaken;
    private boolean isTimedOut;

    /**
     * Constructs a new Quiz object, initializing all lists for questions, responses, correctness, and time.
     */

    public Quiz() {
        questions = new ArrayList<>();
        responses = new ArrayList<>();
        correctness = new ArrayList<>();
        timeTaken = new ArrayList<>();
    }

    /**
     * Adds a new question to the quiz.
     * @param question the question to be added
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Sets the timed-out status of the quiz.
     * @param timedOut the status of whether the quiz is timed out
     */
    public void setTimedOut(boolean timedOut) {
        isTimedOut = timedOut;
    }

    /**
     * Conducts the quiz by displaying questions, accepting responses, and timing each question.
     */
    public void conductQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((char) ('A' + i) + ". " + options[i]);
            }

            String answer = Constant.UNATTEMPTED;
            isTimedOut = false;
            long startTime = System.currentTimeMillis();

            // Start the countdown thread for 10 seconds
            TimerThread timerThread = new TimerThread(10000, this);
            timerThread.start();

            // Capture user response if provided in time
            if (scanner.hasNextLine() && !isTimedOut) {
                answer = scanner.nextLine().trim().toUpperCase();
                timerThread.interrupt(); 
            }

            long endTime = System.currentTimeMillis();
            long timeElapsed = (endTime - startTime) / 1000;

            responses.add(isTimedOut ? Constant.UNATTEMPTED : answer);
            correctness.add(isTimedOut ? false : question.isCorrectAnswer(answer.charAt(0)));
            timeTaken.add(isTimedOut ? 10 : timeElapsed);
        }

        scanner.close();
    }

    /**
     * Displays the results of the quiz, including responses, correctness, and time taken for each question.
     */
    public void showResults() {
        System.out.println(Constant.QUIZ_RESULTS);
        System.out.println(Constant.BORDER);
        System.out.printf(Constant.TABLE_FORMAT, Constant.QUESTION, Constant.RESPONSE, Constant.CORRECT, Constant.TIME);
        System.out.println(Constant.BORDER);

        for (int i = 0; i < questions.size(); i++) {
            String questionText = "Q" + (i + 1);
            String response = responses.get(i);
            boolean isCorrect = correctness.get(i);
            long time = timeTaken.get(i);

            System.out.printf(Constant.TABLE_FORMAT, questionText, response,
                    isCorrect ? Constant.CORRECT : Constant.WRONG, response.equals(Constant.UNATTEMPTED) ? Constant.UNATTEMPTED : time + Constant.SEC);
        }

        System.out.println(Constant.BORDER);
    }
}
