import java.util.*;

public class PlagiarismDetectionSystem {

    // extract n-grams
    public static List<String> generateNGrams(String text, int n) {

        List<String> ngrams = new ArrayList<>();

        String[] words = text.toLowerCase().split(" ");

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words[i + j]).append(" ");
            }

            ngrams.add(gram.toString().trim());
        }

        return ngrams;
    }

    // calculate similarity
    public static double calculateSimilarity(List<String> doc1, List<String> doc2) {

        HashSet<String> set1 = new HashSet<>(doc1);
        HashSet<String> set2 = new HashSet<>(doc2);

        int match = 0;

        for (String gram : set1) {
            if (set2.contains(gram)) {
                match++;
            }
        }

        return (match * 100.0) / set1.size();
    }

    public static void main(String[] args) {

        String essay1 = "machine learning is a powerful tool in modern artificial intelligence";
        String essay2 = "artificial intelligence uses machine learning as a powerful technology";

        int n = 3; // 3-grams

        List<String> grams1 = generateNGrams(essay1, n);
        List<String> grams2 = generateNGrams(essay2, n);

        double similarity = calculateSimilarity(grams1, grams2);

        System.out.println("Document 1 n-grams: " + grams1.size());
        System.out.println("Document 2 n-grams: " + grams2.size());

        System.out.println("Similarity: " + similarity + "%");

        if (similarity > 50) {
            System.out.println("⚠️ Possible Plagiarism Detected");
        } else {
            System.out.println("No plagiarism detected");
        }
    }
}