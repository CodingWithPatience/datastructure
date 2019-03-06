package com.zhihao.datastructure.set;

import com.zhihao.datastructure.tree.BSTree;

/**
 * @auther: zzh
 * @date: 2019/1/30
 */
public class BSTreeSet<E extends Comparable> implements Set<E> {

    private BSTree<E> data;

    public BSTreeSet() {
        data = new BSTree<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean add(E e) {
        return data.insert(e);
    }

    @Override
    public boolean remove(E e) {
        if (contains(e)) {
            data.remove(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    private void print() {
        data.inOrder();
        System.out.println();
    }

    public static void main(String[] args) {
        BSTreeSet<Integer> set = new BSTreeSet<>();
        for (int i=0; i<5; i++) {
            set.add(i);
        }
        set.print();
        set.add(3);
        set.add(5);
        set.print();
    }
}
