package com.zws.example.struct.tree.uf;


/**
 *  并查集
 *    是一种很不一样树型结构，用于处理一些不相交集合（Disjoint Sets）的合并及查询问题
 *
 *  连接问题和路径问题
 *      比路径问题要回答的问题少
 *
 */
public interface UF {

    Integer getSize();
    boolean isConnected(Integer p,Integer q);
    void unionElements(Integer p,Integer q);
}
