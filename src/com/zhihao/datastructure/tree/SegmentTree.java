package com.zhihao.datastructure.tree;

/**
 * @auther: zzh
 * @date: 2019/1/31
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.data = (E[]) new Object[data.length];
        for (int i=0; i<data.length; i++)
            this.data[i] = data[i];
        this.merger = merger;
        this.tree = (E[]) new Object[4*data.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            this.tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid+1, r);
        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length)
            throw new IndexOutOfBoundsException();
        if (queryL > queryR)
            throw new IllegalStateException("Index is illegal! Left index can not greater than right index");
        return query(0, 0, data.length-1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + (r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        if (queryL >= mid+1)
            return query(rightIndex, mid+1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftIndex, l, mid, queryL, queryR);
        else
            return merger.merge(query(leftIndex, l, mid, queryL, mid),
                    query(rightIndex, mid+1, r, mid+1, queryR));
    }

    public void update(int index, E val) {
        if (index < 0 || index >= data.length)
            throw new IllegalStateException("Index is illegal");
        data[index] = val;
        update(0, 0, data.length-1, index, val);
    }

    private void update(int treeIndex, int l, int r, int index, E val) {
        if (l == r) {
            tree[treeIndex] = val;
            return;
        }
        int mid = l + (r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        if (index >= mid+1)
            update(rightIndex, mid+1, r, index, val);
        else
            update(leftIndex, l, mid, index, val);
        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);

    }

    private int leftChild(int treeIndex) {
        return 2*treeIndex+1;
    }

    private int rightChild(int treeIndex) {
        return 2*treeIndex+2;
    }

    public static void main(String[] args) {
        Integer[] nums = {1,5,2,6,3,4,7};
        SegmentTree<Integer> tree = new SegmentTree<>(nums, (a, b) -> a+b);
        System.out.println(tree.query(2, 5));
        tree.update(3, 0);
        System.out.println(tree.query(2, 5));
    }
}
