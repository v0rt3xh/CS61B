import org.junit.Test;
import static org.junit.Assert.*;
public class TestPalindrome {
    @Test
    public void TestInput(){
        Deque<Character> DTest = Palindrome.wordToDeque("V0rtexH");
        DTest.printDeque();
    }
    @Test
    public void TestJudge() {
        boolean result = Palindrome.isPalindrome("Haitao");
        assertEquals(false, result);
        result = Palindrome.isPalindrome("abaca");
        assertEquals(false, result);
    }
    @Test
    public void TestOffOne() {
        OffByOne tool = new OffByOne();
        System.out.println(tool.equalChars('a','a'));
        boolean result = Palindrome.isPalindrome("amama", tool);
        assertEquals(false, result);
    }
    @Test
    public void TestOffN() {
        OffByN tool = new OffByN(5);
        System.out.println(tool.equalChars('a','f'));
        boolean result = Palindrome.isPalindrome("amama", tool);
        assertEquals(false, result);
    }
}
