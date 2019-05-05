package com.zws.example.struct.tree;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/5
 * <p>
 * 线段树（区间树，平衡二叉树）
 * <p>
 * 为什么使用线段树 ？
 * 对于有一类问题，我们关心的是线段（或者区间）
 * <p>
 * 什么是线段树？
 * 线段树是一种二叉搜索树，与区间树相似，它将一个区间划分成一些单元区间，每个单元区间对应线段树中的一个叶结点。
 * 使用线段树可以快速的查找某一个节点在若干条线段中出现的次数，时间复杂度为O(logN)。而未优化的空间复杂度为2N，实际应用时一般还要开4N的数组以免越界，因此有时需要离散化让空间压缩。
 * <p>
 * 经典的线段树问题：
 * 区间染色
 * 有一面墙，长度为n，每次选中一段儿墙进行染色
 * m次操作后，我们可以看见多少种颜色？
 * m次操作后，我们可以在【i，j】区间内看见多少中颜色？
 * 使用数组实现      使用线段树实现
 * 染色操作（更新区间）       O(n)           O（logn）
 * 查询操作 (查询区间)       O(n)           O (logn)
 * 区间查询：
 * 查询一个区间【i，j】的最大值，最小值，或者区间数字和
 * 实质：基于区间的统计查询
 * 2017年注册用户中消费最高的用户？消费最少的用户？ 学习时间最长的用户？
 * 使用数组实现    使用线段树实现
 * 更新         O(n)         O（logn）
 * 查询         O(n)         O (logn)
 * <p>
 * 用数组表示： 将线段树看做满二叉树
 * 如果区间有n个元素 数组表示需要有多少节点 ？
 * 最好情况 2N
 * 最差情况 4N
 * <p>
 * 以下以求和为例
 *
 *
 * 更多线段树相关的问题： 将【i，j】区间中所有元素+3（懒惰更新），二维线段树，动态线段树，树状数组，RMQ（Range Minimum Query）
 *
 *
 */
public class SegmentTree<E> {


    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    /**
     * 更新操作 O(logn)
     *
     * @param arr
     * @param merger
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }
        this.tree = (E[]) new Object[4 * arr.length];
        this.merger = merger;
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间【l...r】的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        int mid = (r - l) / 2 + l;
        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);


    }

    public int getSize() {
        return data.length;
    }


    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }


    /**
     * 返回完全二叉树的数组表示中， 一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return (index * 2) + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return (index * 2) + 2;
    }


    /**
     * 返回区间【queryL,queryR】的值
     * 时间复杂度：O(logn)
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, queryL, queryR, 0, data.length - 1);

    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    public E query(int treeIndex, int queryL, int queryR, int l, int r) {

        if (queryL == l && queryR == r) {
            return tree[treeIndex];
        }

        int rightIndex = rightChild(treeIndex);
        int leftIndex = leftChild(treeIndex);
        int mid = (r - l) / 2 + l;

        if (queryR <= mid) {
            return query(leftIndex, queryL, queryR, l, mid);
        }

        if (queryL > mid) {
            return query(rightIndex, queryL, queryR, mid + 1, r);
        }

        E left = query(leftIndex, queryL, mid, l, mid);
        E right = query(rightIndex, mid + 1, queryR, mid + 1, r);
        return merger.merge(left, right);
    }


    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            data[l] = e;
            return;
        }

        int mid = (r - l) / 2 + l;
        int rightIndex = rightChild(treeIndex);
        int leftIndex = leftChild(treeIndex);
        if (index > mid) {
            set(rightIndex, mid + 1, r, index, e);
        } else {
            set(leftIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[rightIndex],tree[leftIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }


    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 5));
        System.out.println(segmentTree.query(0, 5));
    }


}
