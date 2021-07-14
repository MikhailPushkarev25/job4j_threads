package ru.job4j;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CASCountTest {

    @Test
    public void whenIncrementTest() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> IntStream.range(1, 5).forEach(c -> count.increment()));
        Thread second = new Thread(() -> IntStream.range(1, 5).forEach(c -> count.increment()));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(8));
    }
}