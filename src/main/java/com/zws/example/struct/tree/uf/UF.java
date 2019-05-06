package com.zws.example.struct.tree.uf;


/**
 *  并查集
 *    是一种树型的数据结构，用于处理一些不相交集合（Disjoint Sets）的合并及查询问题
 *    用来回答连接问题
 *
 */
public interface UF {

    int getSize();
    boolean isConnected(int p,int q);
    void unionElements(int p,int q);
}
