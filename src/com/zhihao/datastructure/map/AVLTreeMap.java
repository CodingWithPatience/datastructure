package com.zhihao.datastructure.map;

import com.zhihao.datastructure.tree.AVLTree;

/**
 * @auther: zzh
 * @date: 2019/2/1
 */
public class AVLTreeMap<K extends Comparable, V> implements Map<K, V> {

    private AVLTree<K, V> tree;

    public AVLTreeMap() {
        tree = new AVLTree<>();
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
        return tree.remove(key);
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
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        for (int i=0; i<10; i++) {
            map.put(i, i*2+"");
        }
        map.print();
        System.out.println(map.get(2));
        map.remove(5);
        map.print();
    }
}
