package io.snippets.multithreading.rateLimiting;

/**
 * This calls
 */
public class BucketFilter {

    private int max;
    private Object lock = new Object();
    private long possibleTokens;
    private long lastRequest;

    public BucketFilter(int max) {
        this.max = max;
    }

    public void getToken() throws InterruptedException {
        synchronized (lock) {
            long current = System.currentTimeMillis();
            if (lastRequest == 0) {
                lastRequest = current;
            }
            possibleTokens = (current - lastRequest) / 1000;
            if (possibleTokens > max) {
                possibleTokens = max;
            }

            if (possibleTokens == 0) {
                Thread.sleep(1000);
            } else {
                possibleTokens--;
            }

            lastRequest = current;
            System.out.println("Token: "+ Thread.currentThread().getName());
        }
    }
}
