package ru.job4j;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer result;
        do {
            result = count.get();
        } while (!count.compareAndSet(result, result + 1));
    }

    public int get() {
        return count.get();
    }
}
