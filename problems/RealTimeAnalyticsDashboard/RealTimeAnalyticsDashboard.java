import java.util.*;

public class RealTimeAnalyticsDashboard {

    // page -> visit count
    static HashMap<String, Integer> pageViews = new HashMap<>();

    // page -> unique users
    static HashMap<String, HashSet<String>> uniqueVisitors = new HashMap<>();

    // traffic source -> count
    static HashMap<String, Integer> trafficSource = new HashMap<>();

    public static void processEvent(String url, String userId, String source) {

        // count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // track unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // track traffic source
        trafficSource.put(source, trafficSource.getOrDefault(source, 0) + 1);

    }

    public static void getDashboard() {

        System.out.println("\n=== DASHBOARD ===");

        // sort top pages
        PriorityQueue<Map.Entry<String,Integer>> pq =
                new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        int count = 0;

        while(!pq.isEmpty() && count < 10){

            Map.Entry<String,Integer> entry = pq.poll();

            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page + " -> " + views + " views (" + unique + " unique)");

            count++;

        }

        System.out.println("\nTraffic Sources:");

        for(String source : trafficSource.keySet()){

            System.out.println(source + " -> " + trafficSource.get(source));

        }

    }

    public static void main(String[] args) {

        processEvent("/article/breaking-news","user123","google");
        processEvent("/article/breaking-news","user456","facebook");
        processEvent("/sports/championship","user123","direct");
        processEvent("/sports/championship","user789","google");
        processEvent("/article/breaking-news","user789","google");

        getDashboard();

    }
}