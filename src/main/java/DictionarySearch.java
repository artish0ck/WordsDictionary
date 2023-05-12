import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DictionarySearch {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your string");

        // 1. get possible substrings
        String userInput = scanner.nextLine();
        List<String> userSubstrings = getSubstrings(userInput);
        //System.out.println("Possible words from user input: " + userSubstrings);

        // 2. pick words from dictionary
        List<String> wordsFromDictionary = getWordsFromDictionary("input.txt");
        System.out.println("Words from dictionary: " + wordsFromDictionary);

        // 3. check possible substrings against dictionary
        List<String> wordsFound = getWordsFound(userSubstrings, wordsFromDictionary);


        // 4. resulting output
        if (!wordsFound.isEmpty()) {
            List<String> distinctWordsFound = getDistinctWordsFound(wordsFound);
            System.out.println("Words found: " + distinctWordsFound);
            System.out.println("Amount of unique words found: " + distinctWordsFound.size());
        } else {
            System.out.println("No words found");
        }
    }


    public static List<String> getSubstrings(String userInput) {
        List<String> userSubstrings = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            for (int j = i + 1; j < userInput.length() + 1; j++) {
                userSubstrings.add(userInput.substring(i, j));
            }
        }
        return userSubstrings;
    }

    public static List<String> getWordsFromDictionary(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(Collectors.toList());
    }


    public static List<String> getWordsFound(List<String> userSubstrings, List<String> wordsFromDictionary) {
        List<String> wordsFound = new ArrayList<>();
        for (String userSubstring : userSubstrings) {
            if (wordsFromDictionary.contains(userSubstring)) {
                wordsFound.add(userSubstring);
            }
        }
        return wordsFound;
    }

    public static List<String> getDistinctWordsFound(List<String> wordsFound) {
        return wordsFound
                .stream().distinct().collect(Collectors.toList());
    }
}
