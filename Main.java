/**
 * The Main class runs the quiz application, adds questions, conducts the quiz, and displays results.
 * Owner : Avadhi-Singhal
 * Date of Creation : 14/11/2024
 */

public class Main {
	
	/**
     * Main method to execute the quiz process.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding questions
        quiz.addQuestion(new Question("What is the largest planet in our Solar System?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 'C'));
        quiz.addQuestion(new Question("What is the capital of Japan?", new String[]{"Beijing", "Tokyo", "Seoul", "Bangkok"}, 'B'));
        quiz.addQuestion(new Question("Who developed the theory of relativity?", new String[]{"Isaac Newton", "Galileo Galilei", "Albert Einstein", "Nikola Tesla"}, 'C'));
        quiz.addQuestion(new Question("Which element has the chemical symbol 'O'?", new String[]{"Osmium", "Oxygen", "Oxide", "Obsidian"}, 'B'));
        quiz.addQuestion(new Question("What is the tallest mountain in the world?", new String[]{"K2", "Mount Kilimanjaro", "Mount Everest", "Mount Fuji"}, 'C'));
        quiz.addQuestion(new Question("Who painted the Mona Lisa?", new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"}, 'C'));
        quiz.addQuestion(new Question("What is the currency of the United Kingdom?", new String[]{"Euro", "Dollar", "Pound", "Yen"}, 'C'));
        quiz.addQuestion(new Question("What is the main ingredient in guacamole?", new String[]{"Tomato", "Potato", "Avocado", "Onion"}, 'C'));
        quiz.addQuestion(new Question("What is the most spoken language in the world?", new String[]{"English", "Spanish", "Mandarin", "Hindi"}, 'C'));
        quiz.addQuestion(new Question("What country has the largest population?", new String[]{"USA", "India", "China", "Russia"}, 'C'));

        // Conduct the quiz
        quiz.conductQuiz();

        // Display results
        quiz.showResults();
    }
}

