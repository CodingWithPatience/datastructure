package com.zhihao.datastructure.unionfind;

/**
 * @auther: zzh
 * @date: 2019/2/1
 */
public class UnionFind implements UF {
    private int[] parent;
    private int[] rank;
    private int unionNum;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        unionNum = size;
        for (int i=0; i<size; i++) {
            parent[i] = i;   //初始每一个元素独立形成一个集合
            rank[i] = 1;     //初始每一个元素在不同的树中，高度都为1
        }
    }

    //找到元素p所在树的根节点
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IndexOutOfBoundsException();
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    //路径压缩，将当前节点的父节点变为父节点的父节点，降低树的高度
            p = parent[p];
        }
        return parent[p];
    }

    //元素q与元素p是否在同一颗中，即是否在同一个集合中
    @Override public boolean isConnected(int q, int p) {
        if (q < 0 || q >= parent.length || p < 0 || p >= parent.length)
            throw new IllegalStateException("Index out of bound");
        return find(q) == find(p);
    }

    //将元素q所在集合与元素p所在集合合并
    @Override
    public void union(int q, int p) {
        if (q < 0 || q >= parent.length || p < 0 || p >= parent.length)
            throw new IllegalStateException("Index out of bound");
        int qRoot = find(q);
        int pRoot = find(p);
        if (qRoot == pRoot)
            return;  //元素q、p在同一个集合，不做操作
        //将高度更小的树合并到更高的树
        if (rank[qRoot] > rank[pRoot])
            parent[pRoot] = parent[qRoot];
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = parent[pRoot];
        else {
            parent[qRoot] = parent[pRoot];  //两树的高度相等，将其中一棵树作为子树合并到另一颗树上
            rank[pRoot]++;                  //新的树高度增加1
        }
        unionNum--;
    }

    @Override
    public int countUnions() {
        return unionNum;
    }

}
