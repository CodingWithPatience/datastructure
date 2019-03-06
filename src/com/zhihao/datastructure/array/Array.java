package com.zhihao.datastructure.array;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class Array<E> {

    private int size;
    private int capacity;
    private E[] elements;

    public Array(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = (E[])new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addList(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(E e) {
        addList(e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalStateException("Illegal index exception");
        if (size == capacity) {
            resize(2*capacity);
        }
        for (int i=size; i>index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = e;
        size++;
    }

    public E remove() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalStateException("Illegal index exception");
        E value = elements[index];
        for (int i=index+1; i<size; i++) {
            elements[i-1] = elements[i];
        }
        size--;
        elements[size] = null;
        if (size == capacity/4 && size != 0)
            resize(capacity/2);
        return value;
    }

    public boolean removeElement(E e) {
        int index = contains(e);
        if (index == -1)
            return false;
        remove(index);
        return true;
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalStateException("Illegal index exception");
        return elements[index];
    }

    public E getLast() {
        return get(size-1);
    }

    public E getFirst() {
        return get(0);
    }

    public int contains(E e) {
        for (int i=0; i<size; i++) {
            if (elements[i].equals(e))
                return i;
        }
        return -1;
    }

    private void resize(int newCap) {
        E[] tab = (E[]) new Object[newCap];
        for (int i=0; i<size; i++) {
            tab[i] = elements[i];
        }
        capacity = newCap;
        elements = tab;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size %d, capacity %d\n", size, capacity));
        sb.append("[");
        for (int i=0; i<size; i++) {
            sb.append(elements[i]);
            if (i != size-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
