package com.zhihao.datastructure.set;

import com.zhihao.datastructure.tree.AVLTree;

/**
 * @auther: zzh
 * @date: 2019/2/2
 */
public class AVLTreeSet<E extends Comparable> implements Set<E> {

    private AVLTree<E, Object> tree;

    public AVLTreeSet() {
        tree = new AVLTree<>();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean add(E e) {
        if (!tree.containsKey(e)) {
            tree.insert(e, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        if (tree.containsKey(e)) {
            tree.remove(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E e) {
        return tree.containsKey(e);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    private void print() {
        tree.printTreeIn();
    }

    public static void main(String[] args) {
        AVLTreeSet<String> set = new AVLTreeSet<>();
        set.add("hello");
        set.add("world");
        set.print();
        set.remove("hello");
        set.print();
    }
}
