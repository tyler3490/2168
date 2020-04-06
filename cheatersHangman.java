package cheatersHangman;

import java.io.*;
import java.util.*;

/*Tyler Hyde 
 *Got stuck on the family generation stuff and 
 *used a solution that I found online
 **/

public class cheatersHangman {
	public static int size;
	
	public static void main(String args[]) {
		Set<String> words = new HashSet<>();
		int longest = 0;
		try {
			Scanner scanner = new Scanner(new File("words.txt"));
			while(scanner.hasNextLine()){
				String word = scanner.nextLine();
				if(word.length() > longest) {
					longest = word.length();					
				}		
				words.add(word);
			}	
			scanner.close();		
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println("You are going to play a game of Hangman.");
		words = selectSize(words, longest);
		System.out.println("How many guesses do you want?");
		Scanner in = new Scanner(System.in);
		int guesses = 0;
		guesses = in.nextInt();
		playHangman(words, guesses);
	}
	
	public static void playHangman(Set<String> words, int guesses) {
		List<String> wordArr = new ArrayList<>();
		Set<Character> guessedLetters = new HashSet<>();
		Map<String, List<String>> families = new HashMap<>();
		String b = "";
		String userGuess;
		Character finalGuess = null;
		Character charUserGuess;
		
		for (int i = 0; i < guesses; i++) {
			b += "-";
		}
		for (String word : words) {
			wordArr.add(word);
		}
		
		Scanner in = new Scanner(System.in);
		
		while (guesses > 0 && !win(wordArr, b)) {
			System.out.println("You have " + guesses + " guesses left \n"
					+ "You have guessed the letters: " + guessedLetters.toString() + " \n"
					+ b + "\n"
					+ "Please guess a letter: ");
					
			userGuess = in.next().strip();
			charUserGuess = userGuess.charAt(0);
			while (guessedLetters.contains(charUserGuess)) {
				System.out.println("That letter has already been guessed, enter a different letter.");
				userGuess = in.next().strip();
				charUserGuess = userGuess.charAt(0);
			}
			guessedLetters.add(charUserGuess);
			families = families(wordArr, guessedLetters);
			String choice = chooseFamily(families);
			wordArr = families.get(choice);
			guesses--;
			if (guesses == 0) {
				finalGuess = charUserGuess;
			}
			b = choice;
		}
		if (win(wordArr, b)) {
			System.out.println("You win!");
		}else {
			System.out.println("You lose, the word was: " + chooseWinner(wordArr, finalGuess));
		}
			
	}

	private static String chooseWinner(List<String> wordArr, Character finalGuess) {
		for (String word : wordArr) {
			int count = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) != finalGuess) {
					count++;
				}
			}
			if(count == word.length()) {
				return word;
			}
		}
		return wordArr.get(0);
	}

	private static String chooseFamily(Map<String, List<String>> families) {
		int size = 0;
		String out = "";
		for (Map.Entry<String, List<String>> entry : families.entrySet()) {
			if (entry.getValue().size() > size) {
				size = entry.getValue().size();
				out = entry.getKey();
			}
		}
		return out;
	}

	private static Map<String, List<String>> families(List<String> wordArr, Set<Character> guessedLetters) {
		Map<String, List<String>> out = new HashMap<>();
		for (String word : wordArr) {
			String wordFam = "";
			for (int i = 0; i < word.length(); i++) {
				if (guessedLetters.contains(word.charAt(i))) {
					wordFam += word.charAt(i);
				}else {
					wordFam += "-";
				}
			}
			if (out.containsKey(wordFam)) {
				List<String> list = out.get(wordFam);
				list.add(word);
				out.put(wordFam, list);
			}else {
				List<String> list = new ArrayList<>();
				list.add(word);
				out.put(wordFam, list);
			}
		}
		return out;
	}

	private static boolean win(List<String> wordArr, String b) {
		if (wordArr.size() == 1 && wordArr.get(0).equals(b)) {
			return true;
		}
		return false;
	}

	public static Set<String> selectSize(Set<String> words, int longest) {
		Set<String> out = new HashSet<>();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the length of word you want:");
		int size = in.nextInt();
		for(String word : words) {
			if (word.length() == size) {
				out.add(word);
			}
		}
		while(size <= 1 || size > longest || out.isEmpty()) {
			System.out.println("Please enter another length, there are no words of that length.");
			size = in.nextInt();
			for(String word : words) {
				if (word.length() == size) {
					out.add(word);
				}
			}
		}
		return out;
	}
}
