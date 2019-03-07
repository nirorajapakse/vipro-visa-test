package vipro.visa.org;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Vipro implements IVipro {

	private static final String SPACE = " ";

	HashMap<String, Integer> wordsMap;

	@Override
	public void readFile(File fileName) {
		if (null != fileName) {
			this.wordsMap = new HashMap<String, Integer>();
			
			System.out.println("Reading file -- " + fileName.getName() + " --");
			
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
				int count;
				String st;
				String[] stArray;
				while ((st = bufferedReader.readLine()) != null) {
					stArray = st.split(SPACE);
					for (String word : stArray) {
						word = word.replaceAll("\\W", "").trim();
						if (!word.isEmpty()) {
							if (this.wordsMap.containsKey(word)) {
								count = this.wordsMap.get(word) + 1;
								this.wordsMap.put(word, count);
							} else {
								this.wordsMap.put(word, 1);
							}
						}
					}
				}
				System.out.println();
			} catch (FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
			} catch (IOException e1) {
				System.out.println("Error: " + e1.getMessage());
			}
			
			System.out.println("File reading completed.");
		} else {
			System.out.println("Error: Invalidate input.");
		}
	}

	@Override
	public void displayWords(int num) {
		if (num > 0 && null != this.getWordsMap() && !this.getWordsMap().isEmpty()) {
			System.out.println("\nList of words which repeats " + num + " times.");
			for (Entry<String, Integer> entry : this.getWordsMap().entrySet()) {
				if (entry.getValue() == num) {
					System.out.println(entry.getKey());
				}
			}
		}
	}

	@Override
	public int displayCount(String word) {
		if (null != word && !word.isEmpty() && null != this.getWordsMap() && !this.getWordsMap().isEmpty()) {
			return this.getWordsMap().get(word);
		}
		return 0;
	}

	public HashMap<String, Integer> getWordsMap() {
		return wordsMap;
	}

	public void setWordsMap(HashMap<String, Integer> wordsMap) {
		this.wordsMap = wordsMap;
	}
	
	public static void main(String[] args) {
		Vipro vp = new Vipro();
		
		File testFile = new File(System.getProperty("user.dir") + "/src/vipro/visa/org/test.txt");
		
		// test: reading file
		vp.readFile(testFile);
		
		// test: display words
		vp.displayWords(2);
		
		// test: display count
		System.out.println("\nNumber of word counts: " + vp.displayCount("hello"));
	}

}
