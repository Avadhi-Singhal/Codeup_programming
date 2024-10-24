import java.util.Scanner;

public class QuizApplication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        QuestionBank questionBank = new QuestionBank();
        Quiz quiz = null;

        String[] participants = new String[5];  // Array to store participant names
        int[] scores = new int[5];  // Array to store their scores
        int count = 0;

        // Loop to keep the application running until the user exits
        while (count < 5) {
            System.out.println("Enter your name: ");
            String name = input.nextLine();

            System.out.println(Constant.USER_CHOICE);
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase(Constant.EXIT)) {
                System.out.println(Message.EXIT);
                break;
            }

            switch (choice) {
                case "1":
                    // Option 1: Create a quiz
                    Creator creator = new Creator(name, questionBank);
                    creator.create();
                    quiz = creator.getQuiz();
                    break;

                case "2":
                    // Option 2: Participate in a quiz
                    if (count < 5) {
                        Participant participant = new Participant(name);
                        participants[count] = name;
                        participant.takeQuiz(quiz);
                        count++;
                    } else {
                        System.out.println("Max 5 participants allowed.");
                    }
                    break;

                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
        }

        // After the quiz, plot the graph of participants' scores
        GraphPlotter graphPlotter = new GraphPlotter();
        graphPlotter.plotGraph(participants, scores);
    }
}
