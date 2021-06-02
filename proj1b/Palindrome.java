public class Palindrome {
    public static Deque<Character> wordToDeque (String word) {
        LinkedListDeque<Character> wordRecord = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordRecord.addLast(word.charAt(i));
        }
        return wordRecord;
    }
    /**How can we derive the recursion form?*/
    public static boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> wordR = wordToDeque(word);
        return recurHelper(wordR) == 1;
    }

    private static int recurHelper(Deque<Character> storage) {
        if (storage.size() <= 1) {
            return 1;
        }
        if (storage.removeFirst() == storage.removeLast()) {
            return recurHelper(storage);
        }
        return 0;
    }
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> wordR = wordToDeque(word);
        return recurBycc(wordR,cc) == 1;
    }

    private static int recurBycc(Deque<Character> storage, CharacterComparator cc) {
        if (storage.size() <= 1) {
            return 1;
        }
        if (cc.equalChars(storage.removeFirst(), storage.removeLast())) {
            return recurBycc(storage, cc);
        }
        return 0;
    }
}
