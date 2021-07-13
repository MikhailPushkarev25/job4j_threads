package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements ItThread<T> {

    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    @Override
    public synchronized void add(T value) {
        list.add(value);
    }

    @Override
    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
