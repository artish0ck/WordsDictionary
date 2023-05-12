import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DictionarySearchTest {

    @Test
    void testEmptyInput() {
        // Arrange
        String input = "";
        List<String> expected = Arrays.asList();
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testSingleCharacterInput() {
        // Arrange
        String input = "a";
        List<String> expected = Arrays.asList("a");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testMultipleCharacterInput() {
        // Arrange
        String input = "hello";
        List<String> expected = Arrays.asList("h", "he", "hel", "hell", "hello", "e", "el", "ell", "ello", "l", "ll", "llo", "l", "lo", "o");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input);
        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void testGetWordsFromDictionary() throws IOException {
        List<String> expected = Arrays.asList("hello", "world", "java", "dictionary");
        List<String> actual = DictionarySearch.getWordsFromDictionary("testInput.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void testWordsFound() {
        List<String> userSubstrings = Arrays.asList("cat", "act", "t");
        List<String> wordsFromDictionary = Arrays.asList("cat", "act", "bat", "tab");

        List<String> expected = Arrays.asList("cat", "act");
        List<String> actual = DictionarySearch.wordsFound(userSubstrings, wordsFromDictionary);

        assertEquals(expected, actual);
    }

    @Test
    void testGetDistinctWordsFound() {
        List<String> wordsFound = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "banana", "date"));
        List<String> distinctWordsFound = DictionarySearch.getDistinctWordsFound(wordsFound);
        List<String> expectedDistinctWordsFound = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        assertEquals(expectedDistinctWordsFound, distinctWordsFound);
    }

}