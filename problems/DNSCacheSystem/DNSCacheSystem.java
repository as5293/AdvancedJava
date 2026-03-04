import java.util.*;

class DNSEntry {

    String domain;
    String ip;
    long expiryTime;

    DNSEntry(String domain,String ip,long ttl){

        this.domain = domain;
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis()+ttl;

    }

}

public class DNSCacheSystem {

    static HashMap<String,DNSEntry> cache = new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    public static void main(String[] args) throws Exception {

        resolve("google.com");

        Thread.sleep(2000);

        resolve("google.com");

        Thread.sleep(4000);

        resolve("google.com");

        getCacheStats();

    }

    static void resolve(String domain){

        long now = System.currentTimeMillis();

        if(cache.containsKey(domain)){

            DNSEntry entry = cache.get(domain);

            if(now < entry.expiryTime){

                hits++;

                System.out.println(domain+" -> Cache HIT -> "+entry.ip);

                return;

            }

            else{

                System.out.println(domain+" -> Cache EXPIRED");

                cache.remove(domain);

            }

        }

        misses++;

        String ip = queryUpstreamDNS(domain);

        cache.put(domain,new DNSEntry(domain,ip,3000));

        System.out.println(domain+" -> Cache MISS -> "+ip);

    }

    static String queryUpstreamDNS(String domain){

        return "172.217.14."+new Random().nextInt(100);

    }

    static void getCacheStats(){

        int total = hits+misses;

        double hitRate = (hits*100.0)/total;

        System.out.println("Hit Rate : "+hitRate+"%");

    }

}