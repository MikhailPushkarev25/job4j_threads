package ru.job4j.net;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), User.of(user)) == null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), User.of(user)) != null;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), User.of(user));
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        User form = map.get(fromId);
        User to = map.get(told);

        if (form == null || to == null || form.getAmount() < amount) {
            return false;
        }
        form.setAmount(form.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        return true;
    }

    public static void main(String[] args) {
        UserStorage userStorage = new UserStorage();
        System.out.println(userStorage.add(new User(1, 100)));
        System.out.println(userStorage.add(new User(2, 200)));
        System.out.println();
       // System.out.println(userStorage.update(new User(1, 500)));
        System.out.println();
        //System.out.println(userStorage.delete(new User(1, 500)));
        System.out.println(userStorage.transfer(1, 2, 50));
    }
}
