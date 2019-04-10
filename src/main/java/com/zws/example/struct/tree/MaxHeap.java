package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/4/9
 * <p>
 * 二叉堆
 * 堆中某个节点的值总是不大于或不小于其父节点的值；
 * 堆总是一棵完全二叉树。
 * <p>
 * 将根节点最大的堆叫做最大堆或大根堆，根节点最小的堆叫做最小堆或小根堆。常见的堆有二叉堆、斐波那契堆等。
 * 堆是非线性数据结构，相当于一维数组，有两个直接后继。
 * <p>
 * 用数组实现二叉堆 计算下标
 * 从数组下标0开始
 * parent（i） =  (i-1) / 2
 * left child (i)  = 2*i+1
 * right child(i)  = 2*i+2
 * <p>
 * 从数组下标1开始
 * parent（i） =  i/2
 * left child (i)  = 2*i-1
 * right child(i)  = 2*i+2
 */
public class MaxHeap<E extends Comparable> {


    private Array<E> data;


    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }


    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for(int i=parent(data.getSize());i>=0;i--){
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return (index * 2) + 1;
    }

    private int rightChild(int index) {
        return (index * 2) + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize());
    }

    private void siftUp(int index) {
        int parent = parent(index);
        if (data.get(index).compareTo(data.get(parent)) == 1) {
            data.swap(index, parent);
            siftUp(parent);
        }
    }

    public E findMax() {
        return data.getFirst();
    }

    public E extractMax() {
        E ret = data.removeFirst();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index) {
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int childIndex = data.get(leftIndex).compareTo(data.get(rightIndex)) == 1 ? leftIndex  : rightIndex;
        if(data.get(index).compareTo(data.get(childIndex))==-1){
            data.swap(index,childIndex);
            siftUp(childIndex);
        }
    }

    public E replace(int index,E e) {
        E ret =  data.get(index);
        data.set(index,e);
        siftUp(index);
        siftDown(index);
        return ret;
    }

    public static void main(String[] args) {

    }


}
