package com.zhihao.datastructure.set;

import com.zhihao.datastructure.tree.RBTree;

/**
 * @auther: zzh
 * @date: 2019/2/3
 */
public class RBTreeSet<E extends Comparable> implements Set<E> {

    private RBTree<E, Object> tree;

    public RBTreeSet() {
        tree = new RBTree<>();
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
//            tree.remove(e);
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
        RBTreeSet<String> set = new RBTreeSet<>();
        set.add("hello");
        set.add("world");
        set.print();
        set.remove("hello");
        set.print();
    }
}
