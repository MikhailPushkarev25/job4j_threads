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

    public SimpleBlockQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
           wait();
        }
        notifyAll();
        return queue.poll();
    }
}
