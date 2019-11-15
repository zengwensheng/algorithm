package com.zws.example.struct.tree;

/**
 * @Author: michael.zeng
 * @Date: 2019/11/9 17:31
 *
 * b树
 * 一个m阶b树特征：
 * 1.根结点至少有两个子女。
 * 2.每个中间节点都包含k-1个元素和k个孩子，其中 m/2 <= k <= m
 * 3.每一个叶子节点都包含k-1个元素，其中 m/2 <= k <= m
 * 4.所有的叶子结点都位于同一层。
 * 5.每个节点中的元素从小到大排列，节点当中k-1个元素正好是k个孩子包含的元素的值域分划。
 *
 */
public class BTree {
}
