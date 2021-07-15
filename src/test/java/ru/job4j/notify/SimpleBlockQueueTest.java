package ru.job4j.notify;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleBlockQueueTest {

    @Test
    public void whenQueueBlockTest() throws InterruptedException {
        SimpleBlockQueue<Integer> queue = new SimpleBlockQueue<>();
        Thread producer = new Thread(
                () -> {
                        queue.offer(1);
                        queue.offer(2);
                        queue.offer(3);
                }
        );

        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(queue.poll().intValue(), 3);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockQueue<Integer> queue = new SimpleBlockQueue<>();

        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 10).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}