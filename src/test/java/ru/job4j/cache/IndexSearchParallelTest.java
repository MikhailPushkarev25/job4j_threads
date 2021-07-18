package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class IndexSearchParallelTest {

    @Test
    public void whenExpectedSortThen9() {
        Integer[] array = {
                5, 3, 4, 7, 2, 1, 8, 10, 9, 11
        };

        assertThat(IndexSearchParallel.sort(array, 2), is(4));
    }

    @Test
    public void whenExpectedSortThen15() {
        Integer[] array =
                {
             15, 3, 8, 1, 7, 2, 10, 9, 5, 11, 4, 12, 6, 13
                };

        assertThat(IndexSearchParallel.sort(array, 6), is(12));
    }
}