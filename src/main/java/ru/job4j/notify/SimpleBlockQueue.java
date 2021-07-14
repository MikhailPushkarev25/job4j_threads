package ru.job4j.notify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockQueue<T> {
    private int limit = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockQueue() {
        this.limit = Integer.MAX_VALUE;
    }

    public synchronized void offer(T value) {
        while (queue.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                wait();
        }
        notifyAll();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
