package com.zhihao.datastructure.queue;

public interface Queue<E> {

    void add(E e);
    E remove();
    E poll();
    E peek();
    boolean isEmpty();
    int size();
}
