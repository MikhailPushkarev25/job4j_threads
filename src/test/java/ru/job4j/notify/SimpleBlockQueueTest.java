package ru.job4j.notify;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockQueueTest {

    @Test
    public void whenQueueBlockTest() throws InterruptedException {
        SimpleBlockQueue<Integer> queue = new SimpleBlockQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(1);
                        queue.offer(2);
                        queue.offer(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                        queue.poll();
                        queue.poll();
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(queue.poll().intValue(), 3);
    }
}