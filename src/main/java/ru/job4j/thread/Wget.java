package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author Mikhail Pushkarev
 * @since 12.07.2021
 * @version 2.1
 */
public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    /**
     * В методе происходит скачивание данных параллельно,
     * то есть я ограничиваю скорость скачивания, этим засекаю время
     * и если время меньше указанного, выставляю паузу.
     */
    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("pom_temp.xml")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long start = System.currentTimeMillis();
            long finish = System.currentTimeMillis() - start;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (finish < speed) {
                    Thread.sleep(finish - speed);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed =  Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
