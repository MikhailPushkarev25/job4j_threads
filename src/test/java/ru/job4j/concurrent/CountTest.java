package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CountTest {
    private class ThreadCount extends Thread {
        private final Count count;

        public ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        /* Создаем счетчик*/
        Count count = new Count();
        /* Создаем нити */
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        /* Запускаем нити */
        first.start();
        second.start();
        /* заставляем главную нить дождаться выполнения главных нитей */
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}