package com.zhihao.datastructure.queue;

import java.util.NoSuchElementException;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int size;
    private int front;
    private int tail;
    private int capacity;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        this.capacity = capacity;
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
    }

    @Override
    public void add(E e) {
        if ((tail+1) % (capacity+1) == front)
            resize(capacity*2);
        data[size] = e;
        size++;
        tail = (tail+1) % (capacity+1);
    }

    private void resize(int newCapacity) {
        E[] tab = (E[]) new Object[newCapacity+1];
        int index = 0;
        for (int i=front; i!=tail; i=(i+1)%(capacity+1)) {
            tab[index++] = data[i];
        }
        data = tab;
        capacity = newCapacity;
        front = 0;
        tail = size;
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException("Empty queue not support remove");
        E e = data[front];
        data[front] = null;
        front = (front+1) % (capacity+1);
        size--;
        if (size == capacity/4 && size != 0)
            resize(capacity/2);
        return e;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        return remove();
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue: size=%d, capacity=%d\n", size, capacity));
        sb.append("[");
        for (int i=front; i!=tail; i=(i+1)%(capacity+1)) {
            sb.append(data[i]);
            if (i%(capacity+1) != tail-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i=0; i<10; i++) {
            queue.add(i);
            System.out.println(queue);
        }
        System.out.println(queue.peek());
        for (int i=0; i<10; i++) {
            System.out.println(queue);
            queue.poll();
            if (queue.isEmpty())
                System.out.println(queue);
        }
        System.out.println(queue.peek());
    }
}
