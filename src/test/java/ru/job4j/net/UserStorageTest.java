package ru.job4j.net;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class UserStorageTest {

    @Test
    public void whenAddTest() {
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 100);
       boolean rsl = userStorage.add(user1);
        assertThat(rsl, is(true));
    }

    @Test
    public void whenUpdateTest() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 200));
        boolean rsl = userStorage.update(new User(1, 300));
        assertThat(rsl, is(true));
    }

    @Test
    public void whenDeleteTest() {
        UserStorage userStorage = new UserStorage();
        User user = new User(1, 200);
        userStorage.add(user);
        boolean rsl = userStorage.delete(user);
        assertThat(rsl, is(true));
    }

    @Test
    public void whenTransferTest() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        userStorage.add(user1);
        userStorage.add(user2);

        Thread first = new Thread(() -> userStorage.transfer(user1.getId(), user2.getId(), 1));
        Thread second = new Thread(() -> userStorage.transfer(user2.getId(), user1.getId(), 2));

        first.start();
        first.join();
        second.start();
        second.join();
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }
}