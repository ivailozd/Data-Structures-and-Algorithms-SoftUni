import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class P01FindWordsInFile {
	
	private final static String FILE_NAME = "bin/input.txt";
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void main(String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();
		Map<String, Integer> words = findWordsAccurances(FILE_NAME);
		final long endTime = System.currentTimeMillis();
		
		words.forEach((word, count) -> System.out.println(word + " -> " + count));
		System.out.println("Search execution time: " + (endTime - startTime) + " milliseconds" );
	}

	private static Map<String, Integer> findWordsAccurances(String fileName) throws IOException {
		Map<String, Integer> words = new HashMap<>();
		Path path = Paths.get(fileName);
		String word = null;
		try (Scanner scanner = new Scanner(path, ENCODING.name())){
			while (scanner.hasNext()) {
				word = scanner.next();
				if (words.containsKey(word)) {
					words.put(word, words.get(word) + 1);
				} else {
					words.put(word, 1);
				}
			}
		}
		
		return words;
	}
}
