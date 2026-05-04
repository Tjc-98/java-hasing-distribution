/**
 * Tools to solve the task.
 */

package se.kth;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class Utilities {
    private Utilities() {}

    /**
     * Be able to read a text information from a txt file.
     * @param file the file name that want to be read.
     * @return the string containing the text.
     */
    static String readText(String file) {
        Path textPath = Paths.get(file);
        try {
            String text = Files.readString(textPath);
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * Get the words from the text file and store them in a ST.
     * @param textFileContent the file which contains the text.
     * @return an ST with the words ordered.
     */
    public static ST<String, Integer> getWords(String textFileContent) {
        // Preparation
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        ST<String, Integer> st = new ST<String, Integer>();

        // Get the words.
        while(scanner.hasNext()) {
            String key = scanner.next();
            if (key.length() < minLength) { // Word is at least one.
                continue;
            }
            if (st.contains(key)) { // Word is already existent.
                st.put(key, st.get(key) + 1);
            } else { // Add the word.
                st.put(key, 1);
            }
        }
        return st;
    }

    /**
     * Takes the words and hashes them. The hashes are stored in an array.
     * @param wordsItr The iterator to loop through the keys (words).
     * @param size the number of the words in the ST table.
     * @return the array that stores the hases of the words.
     */
    static int[] hashify(Iterable<String> wordsItr, int size) {
        int[] hashWords = new int[size];
        String[] words = new String[size];
        int j = 0;
        for(String word : wordsItr) { // Get the words in a string format.
            words[j++] = word;
        }

        for(int i = 0; i < size; i++) { // Hash all the words.
            hashWords[i] = words[i].hashCode();
        }
        return hashWords;
    }

    /**
     * Checks if there is duplicates in the hash code calculations.
     * @param array the array with the hash code for the words.
     * @param wordTable the ST table with the words.
     */
    static void checkDuplicates(int[] array, ST<String,Integer> wordTable) {
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] == array[j]) {
                    System.out.println("Words repeated with value: " + array[j]);
                    System.out.println("Words are: " + (String)wordTable.select(i) + "|" + (String)wordTable.select(j));
                    System.out.println(i + "|" + j);
                    break;
                }
            }
        }
    }

    /**
     * Check the distribution of the hashes done for thw words.
     * @param array the array that has the hashes for the words.
     * @param division how many section that the the distribution will be applied to.
     * @return the distributions in an array.
     */
    static int[] checkDistribution(int[] array, int division) {
        int[] counter = new int[division];
        resetArray(counter);

        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            if(num < 0) {
                num = -1 * num;
            }
            int index = num % division;
            counter[index]++;
        }
        return counter;
    }

    /**
     * Reset the values of the array to zeros.
     * @param array the array that wanted to be reset.
     */
    static void resetArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }
}
