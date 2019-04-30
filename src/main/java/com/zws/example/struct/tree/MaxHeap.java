package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;

import java.util.Random;

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

    /**
     * 时间复杂度：
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(data.getSize()); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        return (index - 1) / 2;
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
     * 时间复杂度：O(logn)
     * <p>
     * 入堆
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /*private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parent = parent(index);

        if (data.get(index).compareTo(data.get(parent)) == 1) {
            data.swap(index, parent);
            siftUp(parent);
        }
    }*/


    private void siftUp(int index) {
        while (index != 0 && data.get(index).compareTo(data.get(parent(index))) == 1) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }


    public E findMax() {
        return data.getFirst();
    }

    /**
     * 时间复杂度：O(logn)
     * <p>
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("MaxHeap is empty");
        }
        data.swap(0, data.getSize() - 1);
        E ret = data.removeLast();
        siftDown(0);
        return ret;
    }

    /*private void siftDown(int index) {

        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        if(leftIndex >= data.getSize() && rightIndex>=data.getSize()){
            return;
        }
        int childIndex;
        if(leftIndex>=data.getSize()){
            childIndex = rightIndex;
        } else if(rightIndex>=data.getSize()){
            childIndex = leftIndex;
        }else{
            childIndex = data.get(leftIndex).compareTo(data.get(rightIndex)) == 1 ? leftIndex : rightIndex;
        }

        if (data.get(index).compareTo(data.get(childIndex)) == -1) {
            data.swap(index, childIndex);
            siftDown(childIndex);
        }
    }*/


    private void siftDown(int index) {

        int childIndex;
        while ((childIndex = leftChild(index)) < data.getSize()) {

            if (childIndex + 1 < data.getSize() && data.get(childIndex).compareTo(data.get(childIndex + 1)) == -1)
                childIndex++;
            if(data.get(index).compareTo(data.get(childIndex))!=-1){
                break;
            }
            data.swap(index,childIndex);
            index = childIndex;

        }

       /* while(true){
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);
            if(leftIndex >= data.getSize() && rightIndex>=data.getSize()){
                break;
            }
            int childIndex;
            if(leftIndex>=data.getSize()){
                childIndex = rightIndex;
            } else if(rightIndex>=data.getSize()){
                childIndex = leftIndex;
            }else{
                childIndex = data.get(leftIndex).compareTo(data.get(rightIndex)) == 1 ? leftIndex : rightIndex;
            }

            if (data.get(index).compareTo(data.get(childIndex)) == -1) {
                data.swap(index, childIndex);
                index = childIndex;
                continue;
            }
            break;

        }*/
    }

    /**
     * sh
     * @param index
     * @param e
     * @return
     */
    public E replace(int index, E e) {
        E ret = data.get(index);
        data.set(index, e);
        siftUp(index);
        siftDown(index);
        return ret;
    }

    public static void main(String[] args) {
        int n = 10000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }


        Integer [] integers = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        n = integers.length;
        maxHeap = new MaxHeap<>(integers);

        maxHeap.replace(0,0);

        maxHeap.replace(maxHeap.size()-1,Integer.MAX_VALUE);


        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException();
            }
        }
        System.out.println("Test MaxHeap completed.");
    }


}
