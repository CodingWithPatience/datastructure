package com.zhihao.datastructure.map;

import com.zhihao.datastructure.tree.RBTree;

/**
 * @auther: zzh
 * @date: 2019/2/3
 */
public class RBTreeMap<K extends Comparable, V> implements Map<K, V> {

    private RBTree<K, V> tree;

    public RBTreeMap() {
        tree = new RBTree<>();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return tree.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return tree.containsValue(value);
    }

    @Override
    public V put(K key, V value) {
        return tree.insert(key, value);
    }

    @Override
    public V get(K key) {
        return tree.getValue(key);
    }

    private void print() {
        tree.printTreeIn();
    }

    public static void main(String[] args) {
        RBTreeMap<String, String> map = new RBTreeMap<>();
        map.put("123", "123");
        map.put("hello", "123");
        map.put("456", "123");
        map.put("abc", "dfg");
        map.print();
    }
}
