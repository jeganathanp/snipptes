package io.snippets.multithreading.blockingQueue;


public class BlockingQueue<T> {
    private T[] array;
    private int capacity;
    private int tail, head, size = 0;
    private Object lock = new Object();

    //constructor
    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }


    public void enquque(T obj) throws InterruptedException {
        synchronized (lock) {
            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }

            array[tail] = obj;
            tail++;
            size++;
            lock.notify();
        }
    }

    public T dequeue() throws InterruptedException {
        Object obj;
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }

            if (head == capacity) {
                head = 0;
            }

            obj = array[head];
            head++;
            size--;
            lock.notify();

        }
        return (T) obj;
    }
}
