public class Message {
	public static final String NO_QUESTIONS = "No questions available to add to the quiz. Returning to main menu.";
	
	public static final String NO_QUIZ = "There is no Quiz created yet.";
	
	public static final String[] DEFAULT_QUESTIONS =  {
            "What is the keyword used to create a class in Java?",
            "Which data type is used to create a variable that should store text?",
            "What is the correct way to create an object in Java?",	
            "Which method can be used to find the length of a string in Java?",
            "What is the output of 10 + 20 + \"30\" in Java?"
        };
	
	 public static final String[][] DEFAULT_OPTIONS = {
	            {"class", "Class", "CLASS", "All of the above"},
	            {"String", "String[]", "char", "int"},
	            {"new ClassName()", "ClassName new()", "new Class()", "Class new()"},
	            {"length()", "size()", "getLength()", "length"},
	            {"102030", "30", "10", "Error"}
	        };
	    
	 public static final String[] DEFAULT_ANSWERS = {
	            "All of the above",
	            "String",
	            "new ClassName()",
	            "length()",
	            "102030"
	        };  
	  
	    public static final String EXIT = "Exiting program...";
	    
	    public static final String PARTICIPANT = "Participant ";
	    
	    public static final String PERFORMING_QUIZ = " is performing an quiz.";
	    
	    public static final String START_QUIZ = "Let's start the quiz : ";
	    
	    public static final String AVAILABLE_QUIZ = "Available quizzes: ";
	    
	    public static final String PLACEHOLDER = "Placeholder";
	    
	    public static final String EMAIL_ID ="placeholder@mail.com";
	    
	    public static final String EXIT_QUIZ = "exit";
	    
	    public static final String WELCOME = "Welcome to the Quiz : ";
	    
	    public static final String FIRST_QUESTION = "Already at First Question";
	    
	    public static final String LAST_QUESTION = "Already at Last Question";
	    
	    public static final String QUESTION_NO = "Question ";
	    
	    public static final String NOT_AVAILABLE = " is not available.";
	    
	    public static final String FINISH ="Quiz Finished!";
	    
	    public static final String SCORE = "Total Score: ";
	    
	    public static final String OUT_OF = " out of ";
	    
	    public static final String NEXT_PREVIOUS_QUESTION = "Previous Question\t\t Next Question\t\t Submit";
	    
	    public static final String FULL = "Cannot add more questions, quiz is full.";
	    
	    public static final String LIMIT = "(cannot be empty and must be less than 100 characters):";

}