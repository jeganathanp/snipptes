package io.snippets.multithreading.blockingQueue;

public class TestBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<Integer>(50);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 50 ;i++){
                    try {
                        queue.enquque(i);
                        System.out.println("enqueued " + i);
                    }catch (InterruptedException e){
                       e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 25 ;i++){
                    try {
                        Integer val = queue.dequeue();
                        System.out.println("Thread2 dequeue " + val);
                }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 25 ;i++){
                    try {
                        Integer val = queue.dequeue();
                        System.out.println("Thread3 dequeue " + val);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        t3.start();
        t3.join();

    }
}
