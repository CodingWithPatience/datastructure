package com.zhihao.datastructure.heap;

import java.util.NoSuchElementException;

/**
 * @auther: zzh
 * @date: 2019/2/27
 */
public class MaxHeap<E extends Comparable> {
    private E[] data;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.data = (E[]) new Comparable[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public MaxHeap(E[] arr) {
        this.capacity = arr.length;
        this.data = (E[]) new Comparable[capacity];
        for (int i=0; i<capacity; i++) {
            this.data[i] = arr[i];
        }
        this.size = capacity;
        heapify();
    }

    public void insert(E e) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        data[size] = e;
        size++;
        shiftUp(size-1);
    }

    public E deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("MaxHeap is empty!");
        E max = data[0];
        data[0] = data[size-1];
        size--;
        shiftDown(0);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void heapify() {
        for (int i=(size-1)/2; i>=0; i--) {
            shiftDown(i);
        }
    }

    private void resize(int cap) {
        E[] tab = (E[]) new Object[cap];
        for (int i=0; i<capacity; i++) {
            tab[i] = data[i];
        }
        capacity = cap;
        data = tab;
    }

    private void swap(int l, int r) {
        if (l < 0 || l >= size || r < 0 || r >= size)
            throw new ArrayIndexOutOfBoundsException();
        E tmp = data[l];
        data[l] = data[r];
        data[r] = tmp;
    }

    private void shiftDown(int k) {
        while (leftChild(k) <= size-1) {
            int left = leftChild(k);
            int right = rightChild(k);
            if (right <= size-1) {
                if (data[k].compareTo(data[left]) < 0 && data[left].compareTo(data[right]) >= 0) {
                    swap(k, left);
                    k = left;
                }
                else if (data[k].compareTo(data[right]) < 0 && data[right].compareTo(data[left]) >= 0) {
                    swap(k, right);
                    k = right;
                }
                else
                    break;
            }
            else if (data[k].compareTo(data[left]) < 0) {
                swap(k, left);
                k = left;
            }
            else
                break;
        }
    }

    private void shiftUp(int k) {
        while (k > 0) {
            int parent = parent(k);
            if (data[k].compareTo(data[parent]) > 0) {
                swap(k, parent);
                k = parent;
            }
            else
                break;
        }
    }

    private int parent(int k) {
        return (k-1) / 2;
    }

    private int leftChild(int k) {
        return 2 * k + 1;
    }

    private int rightChild(int k) {
        return 2 * k + 2;
    }
}
