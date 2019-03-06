package com.zhihao.datastructure.unionfind;

public interface UF {
    boolean isConnected(int q, int p);
    void union(int q, int p);
    int countUnions();
}
