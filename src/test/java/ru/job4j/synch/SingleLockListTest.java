package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> singleLockList = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> singleLockList.add(1));
        Thread second = new Thread(() -> singleLockList.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new HashSet<>();
        singleLockList.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }
}