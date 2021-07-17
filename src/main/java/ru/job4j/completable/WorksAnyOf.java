package ru.job4j.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WorksAnyOf {

    public static CompletableFuture<String> whoWashHands(String name) {
        return CompletableFuture.supplyAsync(
                () ->  {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return name + ", моет руки";
                }
        );
    }

    public static void anyOfExample() throws Exception {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                whoWashHands("Папа"), whoWashHands("Мама"),
                whoWashHands("Ваня"), whoWashHands("Боря")
        );
        System.out.println("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    public static void main(String[] args) throws Exception {
        anyOfExample();
    }
}
