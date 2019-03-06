package com.zhihao.datastructure.stack;

/**
 * @auther: zzh
 * @date: 2018/12/29
 */
class MinStack {

    private static final int defaultCap = 8;

    int size;

    int capacity;

    int[] table;

    int min;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.table = new int[defaultCap];
        this.capacity = defaultCap;
        this.size = 0;
        this.min = 0;
    }

    public void push(int x) {
        if (isEmpty()) {
            min = x;
        }
        else {
            if (min > x)
                min = x;
        }
        if (size == capacity) {
            resize();
        }
        table[size++] = x;
    }

    private void resize() {
        final int[] tab = table;
        int newCapacity = capacity * 2;
        int [] elements = new int[newCapacity];
        for (int i=0; i<tab.length; i++) {
            elements[i] = tab[i];
        }
        table = elements;
        capacity = newCapacity;
    }

    public void pop() {
        if (isEmpty())
            return;
        if (top() == min) {
            size--;
            if (!isEmpty()) {
                min = table[0];
                for (int i=0; i<size; i++) {
                    if (min > table[i])
                        min = table[i];
                }
            }
            else
                min = 0;
        }
        else
            size--;
    }

    public int top() {
        if (isEmpty())
            return -1;
        return table[size-1];
    }

    public int getMin() {
        if (isEmpty())
            return -1;
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
