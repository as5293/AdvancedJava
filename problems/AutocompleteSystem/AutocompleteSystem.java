import java.util.*;

public class AutocompleteSystem {

    // store query -> frequency
    static HashMap<String, Integer> queryFrequency = new HashMap<>();

    // add or update query
    public static void updateFrequency(String query) {

        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);

    }

    // search suggestions
    public static List<String> search(String prefix) {

        List<String> results = new ArrayList<>();

        for (String query : queryFrequency.keySet()) {

            if (query.startsWith(prefix)) {
                results.add(query);
            }

        }

        // sort by frequency
        results.sort((a, b) -> queryFrequency.get(b) - queryFrequency.get(a));

        // return top 10
        if (results.size() > 10) {
            return results.subList(0, 10);
        }

        return results;
    }

    public static void main(String[] args) {

        // sample queries
        updateFrequency("java tutorial");
        updateFrequency("java tutorial");
        updateFrequency("java download");
        updateFrequency("javascript tutorial");
        updateFrequency("java stream api");
        updateFrequency("java spring boot");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter search prefix:");

        String prefix = sc.nextLine();

        List<String> suggestions = search(prefix);

        System.out.println("\nSuggestions:");

        for (String s : suggestions) {
            System.out.println(s + " (" + queryFrequency.get(s) + " searches)");
        }

    }
}