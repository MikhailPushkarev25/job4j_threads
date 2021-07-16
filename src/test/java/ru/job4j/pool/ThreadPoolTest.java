package ru.job4j.pool;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadPoolTest {

    @Test
    public void whenThreadsTest() throws InterruptedException {
        List<Integer> threads = new LinkedList<>();
        ThreadPool pool = new ThreadPool();
        pool.run();
        for (int i = 0; i < 10; i++) {
            int rsl = i;
            Thread first = new Thread(() -> threads.add(rsl));
            first.start();
            first.join();
        }
        pool.shutdown();
        assertThat(threads.size(), is(10));
    }
}