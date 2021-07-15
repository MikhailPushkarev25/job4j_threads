package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddModelTest() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 0);
        Base model2 = new Base(2, 0);
        Base model3 = new Base(3, 0);
        cache.add(model1);
        cache.add(model2);
        cache.add(model3);
        assertThat(cache.getKey(model1.getId()), is(model1));
        assertThat(cache.getKey(model2.getId()), is(model2));
        assertThat(cache.getKey(model3.getId()), is(model3));
    }

    @Test
    public void whenDeleteModelTest() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 0);
        cache.add(model1);
        assertThat(cache.getKey(model1.getId()), is(model1));
        cache.delete(model1);
        assertNull(cache.getKey(model1.getId()));
    }

    @Test
    public void whenUpdateModelTest() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 0);
        Base model2 = new Base(2, 0);
        model2.setName("User");
        cache.add(model1);
        cache.add(model2);
        assertThat(cache.getKey(model1.getId()), is(model1));
        cache.update(model2);
        assertThat(cache.getKey(model2.getId()).getName(), is(model2.getName()));

    }
}