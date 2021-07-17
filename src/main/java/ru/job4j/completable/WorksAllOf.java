package ru.job4j.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WorksAllOf {

    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + " Моет руки");
                }
        );
    }

    public static void allOfExample() throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                washHands("Папа"), washHands("Мама"),
                washHands("Ваня"), washHands("Боря")
        );
        TimeUnit.SECONDS.sleep(3);
    }

    public static void main(String[] args) throws Exception {
        allOfExample();
    }
}
