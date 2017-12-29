public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> characterDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            characterDeque.addLast(word.charAt(i));
        }
        return characterDeque;
    }

    public static boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        int lastCharacterIndex = word.length() - 1;
        if (word.charAt(0) == word.charAt(lastCharacterIndex)) {
            return isPalindrome(word.substring(1, lastCharacterIndex));
        }
        return false;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }
        int lastCharacterIndex = word.length() - 1;
        if (cc.equalChars(word.charAt(0), word.charAt(lastCharacterIndex))) {
            return isPalindrome(word.substring(1, lastCharacterIndex), cc);
        }
        return false;
    }

    public static void main(String[] args) {
        String word = "ac";
        Deque<Character> characterDeque = wordToDeque(word);
        characterDeque.printDeque();
        if (isPalindrome(word)) {
            System.out.println("Hooray, it's a palindrome!");
        } else {
            System.out.println("Oops, it's not a palindrome.");
        }
        CharacterComparator cc = new OffByOne();
        if (isPalindrome(word, cc)) {
            System.out.println("Hooray, it's a off-by-1 palindrome!");
        } else {
            System.out.println("Oops, it's not a off-by-1 palindrome.");
        }
    }
}
