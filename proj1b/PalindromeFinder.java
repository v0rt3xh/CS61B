/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        int[] counter = new int[26];
        for (int i = 0; i < counter.length; i++) {
            In in = new In("words");
            OffByN tool = new OffByN(i);
            int freq = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && Palindrome.isPalindrome(word, tool)) {
                    //System.out.println(word);
                    freq++;
                }
            }
            counter[i] = freq;
        }
        int index = 0;
        for (int element : counter) {
            System.out.print("Difference of " + index + " with frequency :");
            System.out.println(element);
            index++;
        }

    }
} 
