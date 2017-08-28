import com.algosec.StringChecker;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 8/27/2017.
 */
public class StringCheckerTest {

    private static List<String> dictionary = Arrays.asList("this", "is", "a", "valid", "sentence");
    private static String testString = "thsisiavaildsentence";

    @Test
    public void emptyStringWithNoDictionaryTest(){
        StringChecker checker = new StringChecker();
        assertTrue(checker.checkString(""));
    }


    @Test
    public void emptyStringWithDictionaryTest(){
        StringChecker checker = new StringChecker(dictionary);
        assertTrue(checker.checkString(""));
    }

    @Test
    public void noDictionaryTest(){
        StringChecker checker = new StringChecker();
        assertFalse(checker.checkString(testString));
    }

    @Test
    public void testStringTest(){
        StringChecker checker = new StringChecker(dictionary);
        assertTrue(checker.checkString(testString));
    }

    @Test
    public void testStringWithChangedDictionaryTest(){
        StringChecker checker = new StringChecker(dictionary);
        assertTrue(checker.checkString(testString));

        // add a couple of words to the dictionary
        List<String> newDictionary = new ArrayList<>(dictionary);
        newDictionary.addAll(Arrays.asList("the", "hte", "some", "more", "words"));

        checker = new StringChecker(newDictionary);
        assertTrue(checker.checkString(testString));
    }

    @Test
    public void dictionaryTest(){
        dictionary = Arrays.asList("abc","acb","cba","cab","bac","bca", "abcd", "bc");
        StringChecker checker = new StringChecker(dictionary);
        checker.checkString("acccccbd");
    }


}
