package com.zhihao.datastructure.map;

import java.util.Arrays;

/**
 * @auther: zzh
 * @date: 2019/1/26
 */
public class SimpleHashMap {
    private int size;
    private int capacity;

    private Entity[] elements;

    private static float rate = 0.75f;

    /** Initialize your data structure here. */
    public SimpleHashMap() {
        this.capacity = 11;
        this.elements = new Entity[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        if (size == capacity*rate)
            resize();
        int index = hash(key);
        if (elements[index] != null) {
            for (Entity entity = elements[index]; entity!=null; entity = entity.next) {
                if (entity.key == key) {
                    entity.val = value;
                    return;
                }
            }
        }
        Entity entity = new Entity(key, value);
        entity.next = elements[index];
        elements[index] = entity;
        size++;
    }

    private void resize() {
        int newCap = capacity*2;
        final Entity[] tmp = elements;
        Entity[] elem = Arrays.copyOf(tmp, capacity);
        Entity[] newElem = new Entity[newCap];
        size = 0;
        capacity = newCap;
        elements = newElem;
        for (int i=0; i<elem.length; i++) {
            if (elem[i] != null) {
                for (Entity entity = elem[i]; entity!=null; entity = entity.next) {
                    put(elem[i].key, elem[i].val);
                }
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        if (elements[index] != null) {
            for (Entity entity = elements[index]; entity!=null; entity = entity.next) {
                if (entity.key == key) {
                    return entity.val;
                }
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        if (elements[index] != null) {
            Entity prev = null;
            for (Entity entity = elements[index]; entity!=null; entity = entity.next) {
                if (entity.key == key) {
                    if (prev != null) {
                        prev.next = entity.next;
                        entity.next = null;
                        return;
                    }
                    elements[index] = entity.next;
                    return;
                }
                prev = entity;
            }
        }
    }

    private int hash(int key) {
        return key ^ this.capacity;
    }

    private class Entity {
        int key;
        int val;
        Entity next;

        public Entity(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
