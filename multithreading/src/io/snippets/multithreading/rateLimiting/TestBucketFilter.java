package io.snippets.multithreading.rateLimiting;

import java.util.HashSet;
import java.util.Set;

public class TestBucketFilter {
    public static void main(String[] args) throws  InterruptedException{
        BucketFilter filter = new BucketFilter(4);
        Set<Thread> threadCollection = new HashSet<Thread>();

        for(int i =0; i < 50; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        filter.getToken();

                    }catch (InterruptedException e){

                    }
                }
            });
            t.setName("Thread_"+i);
            threadCollection.add(t);

        }
        for( Thread t : threadCollection){
            t.start();
        }

        for(Thread t : threadCollection) {
            t.join();
        }
    }
}
