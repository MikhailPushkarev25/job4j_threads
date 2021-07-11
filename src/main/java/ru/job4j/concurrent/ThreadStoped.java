package ru.job4j.concurrent;

public class ThreadStoped {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ... ");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                                Thread.currentThread().interrupted();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}
