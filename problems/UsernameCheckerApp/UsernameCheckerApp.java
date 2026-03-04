import java.util.*;

public class UsernameCheckerApp {

    static HashSet<String> usernames = new HashSet<>();
    static HashMap<String, Integer> attempts = new HashMap<>();

    public static void main(String[] args) {

        usernames.add("john_doe");
        usernames.add("admin");
        usernames.add("alice");

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));

        System.out.println(suggestAlternatives("john_doe"));

        System.out.println(getMostAttempted());
    }

    static boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !usernames.contains(username);
    }

    static List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }

        return suggestions;
    }

    static String getMostAttempted() {

        int max = 0;
        String result = "";

        for (String key : attempts.keySet()) {
            if (attempts.get(key) > max) {
                max = attempts.get(key);
                result = key;
            }
        }

        return result;
    }
}