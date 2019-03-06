package com.zhihao.datastructure.set;

public interface Set<E extends Comparable> {
    int size();
    boolean add(E e);
    boolean remove(E e);
    boolean contains(E e);
    boolean isEmpty();
}
