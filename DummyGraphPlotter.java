/***
 * This class plots the graph of top five students based on their numbers.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 23/10/2024
 */

public class DummyGraphPlotter {

	/***
	 * This function prints graph on console.
	 * 
	 * @param participants array stores name of participants
	 * @param scores array stores the scores of participants
	 */
    public void plotGraph(String[] participants, int[] scores) {
        int maxScore = findMaxScore(scores);
        String topper = findTopper(participants, scores);

        for (int i = maxScore; i >= 1; i--) {
            for (int j = 0; j < scores.length; j++) {
                if (scores[j] >= i) {
                    System.out.print(Constant.ASTERICS); 
                } else {
                    System.out.print(Constant.SPACE); 
                }
            }
            System.out.println(); 
        }

        for (int j = 0; j < participants.length; j++) {
            System.out.print(" " + participants[j].substring(0, Utility.minimum(participants[j].length())) + "  "); 
        }
        System.out.println(Constant.NEW_LINE);
        
        System.out.println(Constant.TOPPER + topper + Constant.MARKS + maxScore);
    }
    
    /***
     * This function finds the mximum score.
     * 
     * @param scores array stores the score of participants
     * @return max returns maximum score from scores array
     */
    private int findMaxScore(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }
    
    /***
     * This function finds the participant with maximum scores.
     * 
     * @param participants array stores name of participants
     * @param scores array stores the scores of participants
     * @return topper - name of the topper
     */
    private String findTopper(String[] participants, int[] scores) {
    	int maxScore = findMaxScore(scores);
    	String topper = null;
    	for(int i = 0; i < participants.length; i++) {
    		if(scores[i] == maxScore) {
    			topper = participants[i];
    		}
    	}
    	return topper;
    }
    
    // this is the main method
    public static void main(String[] args) {
        String[] participants = {"Alice", "Bob", "Charlie", "David", "Eve"};
        int[] scores = {5, 3, 9, -7, 6};

        GraphPlotter graphPlotter = new GraphPlotter();
        graphPlotter.plotGraph(participants, scores);
    }
}
