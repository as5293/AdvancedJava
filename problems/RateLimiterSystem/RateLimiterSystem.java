import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate; // tokens per second

    public TokenBucket(int maxTokens, int refillRate) {

        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();

    }

    public synchronized boolean allowRequest() {

        refillTokens();

        if (tokens > 0) {

            tokens--;
            return true;

        }

        return false;

    }

    private void refillTokens() {

        long now = System.currentTimeMillis();

        long seconds = (now - lastRefillTime) / 1000;

        if (seconds > 0) {

            int refill = (int) seconds * refillRate;

            tokens = Math.min(maxTokens, tokens + refill);

            lastRefillTime = now;

        }

    }

}

public class RateLimiterSystem {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    static int LIMIT = 1000;

    public static boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT, LIMIT / 3600));

        TokenBucket bucket = clients.get(clientId);

        boolean allowed = bucket.allowRequest();

        if (allowed) {

            System.out.println(clientId + " -> Allowed");

        } else {

            System.out.println(clientId + " -> Denied (Rate limit exceeded)");

        }

        return allowed;

    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            checkRateLimit("abc123");

        }

    }

}