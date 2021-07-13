package ru.job4j.synch;

import java.util.Iterator;

public interface ItThread<T> {

    void add(T value);

    T get(int index);

    Iterator<T> iterator();
}
