package ru.job4j;

public class CountDemo {
    public static void main(String[] args) {
        CountBarrier count = new CountBarrier(1);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + "started");
                    count.count();
                },
                "Master "
        );

        Thread second = new Thread(
                () -> {
                    count.await();
                    System.out.println(Thread.currentThread().getName() + "slave");
                },
                "Slave "
        );

        first.start();
        second.start();
    }
}
