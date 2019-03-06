package com.zhihao.datastructure.stack;

public interface Stack<E> {

    E pop();
    void push(E e);
    E peek();
    boolean isEmpty();
    int size();
}
