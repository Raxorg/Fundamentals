package com.epicness.fundamentals.utils;

import com.badlogic.gdx.utils.OrderedMap;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MapUtils {

    public static <T, U> void loopMap(OrderedMap<T, U> map, Consumer<U> consumer) {
        for (int i = 0; i < map.size; i++) {
            consumer.accept(map.get(map.orderedKeys().get(i)));
        }
    }

    public static <T, U> void loopMap(OrderedMap<T, U> map, BiConsumer<T, U> consumer) {
        for (int i = 0; i < map.size; i++) {
            T key = map.orderedKeys().get(i);
            consumer.accept(key, map.get(key));
        }
    }
}