package com.zhihao.datastructure.map;

public interface Map<K, V> {

    int size();
    boolean isEmpty();
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    V put(K key, V value);
    V get(K key);
}
