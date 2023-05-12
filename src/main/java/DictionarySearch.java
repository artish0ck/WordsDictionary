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

        // 1. get possible substrings and check case sensitivity
        String userInput = scanner.nextLine();
        boolean response = isCaseSensitive(scanner);
        List<String> userSubstrings = getSubstrings(userInput, response);
        //System.out.println("Possible words from user input: " + userSubstrings);


        // 2. pick words from dictionary
        List<String> wordsFromDictionary = getWordsFromDictionary("input.txt", response);
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


    public static List<String> getSubstrings(String userInput, boolean response) {
        List<String> userSubstrings = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            for (int j = i + 1; j < userInput.length() + 1; j++) {
                userSubstrings.add(userInput.substring(i, j));
            }
        }
        if (!response) {
            userSubstrings = userSubstrings.stream().map(String::toLowerCase).collect(Collectors.toList());
        }
        return userSubstrings;
    }

    public static List<String> getWordsFromDictionary(String filePath, boolean response) throws IOException {
        List<String> words = Files.lines(Paths.get(filePath)).collect(Collectors.toList());
        if (!response) {
            words = words.stream().map(String::toLowerCase).collect(Collectors.toList());
        }
        return words;
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

    public static boolean isCaseSensitive(Scanner scanner) {
        System.out.println("Do you want the search to be case sensitive? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        }
        else if (response.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            return isCaseSensitive(scanner);
        }
    }

    public static List<String> getDistinctWordsFound(List<String> wordsFound) {
        return wordsFound
                .stream().distinct().collect(Collectors.toList());
    }

}
