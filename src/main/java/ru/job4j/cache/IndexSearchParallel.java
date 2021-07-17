package ru.job4j.cache;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexSearchParallel extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final Integer form;
    private final Integer to;
    private final Integer element;

    public IndexSearchParallel(Integer[] array, Integer form, Integer to, Integer element) {
        this.array = array;
        this.form = form;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if ((form - to) < 10) {
            for (int i = form; i < to; i++) {
                if (array[i].equals(element)) {
                    return i;
                }
            }
        }
        int mid = (form + to) / 2;
        IndexSearchParallel leftSort =
                new IndexSearchParallel(array, form, mid, element);
        IndexSearchParallel rightSort =
                new IndexSearchParallel(array, mid + 1, to, element);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return left != -1 ? left : right;
    }

    public static int sort(Integer[] array, Integer element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexSearchParallel(array, 0, array.length - 1, element));
    }
}
