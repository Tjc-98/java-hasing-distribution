/**
 * The task is to check how the hash function in java calculates the hashes for the words(Strings), and check how they
 * are distributed.
 */

package se.kth;

public class Main {
    public static void main(String[] args) {
        test(10);
    }

    /**
     * Test the evenly distribution of the hash values.
     * @param division how many parts will be the division.
     */
    static void test(int division) {
        // Preparations
        String text = Utilities.readText("theFilteredText.txt");
        ST<String, Integer> wordTable = Utilities.getWords(text);

        // Test the hashes.
        int[] hashWords = Utilities.hashify(wordTable.keys(), wordTable.size());
        Utilities.checkDuplicates(hashWords, wordTable);
        int[] distribution =  Utilities.checkDistribution(hashWords,division);
        printArray(distribution);
        System.out.println(" ");
    }

    /**
     * Print out the elements of the array to the console.
     * @param array that wanted to be displayed.
     */
    static void printArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}