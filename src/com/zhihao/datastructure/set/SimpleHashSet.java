package com.zhihao.datastructure.set;

import java.util.Arrays;

/**
 * @auther: zzh
 * @date: 2019/1/26
 */
public class SimpleHashSet {
    private int size;
    private int capacity;

    private Node[] table;
    private static float rate = 0.75f;

    /** Initialize your data structure here. */
    public SimpleHashSet() {
        this.capacity = 11;
        this.table = new Node[capacity];
    }

    public void add(int key) {
        if (size == capacity*rate)
            resize();
        int index = hash(key);
        if (table[index] != null) {
            for (Node node = table[index]; node != null; node = node.next) {
                if (node.val == key)
                    return;
            }
        }
        Node node = new Node(key);
        node.next = table[index];
        table[index] = node;
        size++;
    }

    private void resize() {
        int newCap = capacity * 2;
        Node[] newTab = new Node[newCap];
        final Node[] tmp = table;
        Node[] tab = Arrays.copyOf(tmp, capacity);
        size = 0;
        capacity = newCap;
        table = newTab;
        for (int i=0; i<tab.length; i++) {
            if (tab[i] != null) {
                for (Node node = tab[i]; node != null; node = node.next) {
                    add(node.val);
                }
            }
        }
    }

    public void remove(int key) {
        int index = hash(key);
        if (table[index] != null) {
            Node prev = null;
            for (Node node = table[index]; node != null; node = node.next) {
                if (node.val == key) {
                    if (prev != null) {
                        prev.next = node.next;
                        return;
                    }
                    table[index] = node.next;
                    return;
                }
                prev = node;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Node node = table[index]; node != null; node = node.next) {
                if (node.val == key)
                    return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return key % this.capacity;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
