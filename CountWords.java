public class CountWords {

    // Method to count words in a given string handling any type 
    public static void countWords(String input) {
        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            boolean isAlphanumeric = (ch >= 'A' && ch <= 'Z') || 
                                     (ch >= 'a' && ch <= 'z') || 
                                     (ch >= '0' && ch <= '9');

            if (isAlphanumeric) {
                if (!inWord) {
                    count++; 
                    inWord = true; 
                }
            } else {
                inWord = false; 
            }
        }

        System.out.println("Word count: " + count);
    }

    public static void main(String[] args) {
        String text = "r m l v d g b t  ";
        countWords(text);
    }
}
