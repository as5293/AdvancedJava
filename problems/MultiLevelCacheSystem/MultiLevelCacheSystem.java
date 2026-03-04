import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V>{

    int capacity;

    LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity=capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size()>capacity;
    }

}

public class MultiLevelCacheSystem {

    static LRUCache<String,String> L1 = new LRUCache<>(3);
    static LRUCache<String,String> L2 = new LRUCache<>(5);

    static HashMap<String,String> database = new HashMap<>();

    static{

        database.put("video_101","Video Data 101");
        database.put("video_102","Video Data 102");
        database.put("video_123","Video Data 123");

    }

    static String getVideo(String videoId){

        if(L1.containsKey(videoId)){

            System.out.println("L1 Cache HIT");
            return L1.get(videoId);

        }

        if(L2.containsKey(videoId)){

            System.out.println("L1 MISS → L2 HIT");

            String data=L2.get(videoId);

            L1.put(videoId,data);

            return data;

        }

        System.out.println("L1 MISS → L2 MISS → DB HIT");

        String data=database.get(videoId);

        if(data!=null){

            L2.put(videoId,data);
            L1.put(videoId,data);

        }

        return data;

    }

    public static void main(String[] args){

        System.out.println(getVideo("video_123"));
        System.out.println(getVideo("video_123"));
        System.out.println(getVideo("video_101"));

    }

}