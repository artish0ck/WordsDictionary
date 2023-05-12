import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DictionarySearchTest {

    @Test
    void testEmptyInput() {
        // Arrange
        String input = "";
        boolean response = true;
        List<String> expected = Arrays.asList();
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input, response);
        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void testSingleCharacterInputCaseSensitive() {
        // Arrange
        String input = "a";
        boolean response = true;
        List<String> expected = Arrays.asList("a");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input, response);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testSingleCharacterInputCaseInsensitive() {
        // Arrange
        String input = "A";
        List<String> expected = Arrays.asList("a");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input, false);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testMultipleCharacterInputCaseSensitive() {
        // Arrange
        String input = "hello";
        boolean response = true;
        List<String> expected = Arrays.asList("h", "he", "hel", "hell", "hello", "e", "el", "ell", "ello", "l", "ll", "llo", "l", "lo", "o");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input, response);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetWordsFromDictionaryCaseInsensitive() throws IOException {
        List<String> expected = Arrays.asList("hello", "world", "java", "dictionary");
        List<String> actual = DictionarySearch.getWordsFromDictionary("testInputCase.txt", false);
        assertEquals(expected, actual);
    }

    @Test
    void testMultipleCharacterInputCaseInsensitive() {
        // Arrange
        String input = "HELLO";
        boolean response = false;
        List<String> expected = Arrays.asList("h", "he", "hel", "hell", "hello", "e", "el", "ell", "ello", "l", "ll", "llo", "l", "lo", "o");
        // Act
        List<String> actual = DictionarySearch.getSubstrings(input, response);
        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void testGetWordsFromDictionary() throws IOException {
        boolean response = true;
        List<String> expected = Arrays.asList("hello", "world", "java", "dictionary");
        List<String> actual = DictionarySearch.getWordsFromDictionary("testInput.txt", response);
        assertEquals(expected, actual);
    }

    @Test
    public void testWordsFound() {
        List<String> userSubstrings = Arrays.asList("cat", "act", "t");
        List<String> wordsFromDictionary = Arrays.asList("cat", "act", "bat", "tab");

        List<String> expected = Arrays.asList("cat", "act");
        List<String> actual = DictionarySearch.getWordsFound(userSubstrings, wordsFromDictionary);

        assertEquals(expected, actual);
    }

    @Test
    void testGetDistinctWordsFound() {
        List<String> wordsFound = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "banana", "date"));
        List<String> distinctWordsFound = DictionarySearch.getDistinctWordsFound(wordsFound);
        List<String> expectedDistinctWordsFound = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        assertEquals(expectedDistinctWordsFound, distinctWordsFound);
    }

    @Test
    public void testIsCaseSensitiveYes() {
        String input = "yes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assert (DictionarySearch.isCaseSensitive(scanner));
    }

    @Test
    public void testIsCaseSensitiveNo() {
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertFalse(DictionarySearch.isCaseSensitive(scanner));
    }

    /*  In this test case, the input is "invalid\nyes\n", which means that the user enters an invalid response
       first and then enters "yes" in the second attempt. This scenario tests the recursion in the
       isCaseSensitive method.*/
    @Test
    public void testIsCaseSensitiveInvalidResponse() {
        String input = "invalid\nyes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        boolean result = DictionarySearch.isCaseSensitive(scanner);
        assertEquals(true, result);
    }
}