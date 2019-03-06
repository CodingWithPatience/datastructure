package com.zhihao.datastructure.queue;

import com.zhihao.datastructure.list.LinkedList;

import java.util.NoSuchElementException;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class LinkedListQueue<E> implements Queue<E> {

    private LinkedList<E> queue;

    public LinkedListQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        queue.addLast(e);
    }

    @Override
    public E remove() {
        try {
            return queue.removeFirst();
        } catch (IllegalStateException e) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        return remove();
    }

    @Override
    public E peek() {
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue: front [");
        sb.append(queue.toString()).append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        for (int i=0; i<10; i++) {
            queue.add(i);
            System.out.println(queue);
        }
        for (int i=0; i<10; i++) {
            queue.poll();
            System.out.println(queue);
        }
    }
}
