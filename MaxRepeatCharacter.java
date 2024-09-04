public class MaxRepeatCharacter {

    public static char maxRepeat(String input) {
     
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Input string is empty.");
        }

        int maxCount = 0; 
        char maxChar = input.charAt(0);

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int count = 0;

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == currentChar) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxChar = currentChar;
            }
        }

        return maxChar;
    }

    public static void main(String[] args) {
        String text = "success";
        System.out.println("Maximum repeated character in string is: " + maxRepeat(text)); // Output: s
    }
}
