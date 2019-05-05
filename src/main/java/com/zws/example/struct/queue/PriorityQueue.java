package com.zws.example.struct.queue;

import com.zws.example.struct.tree.MaxHeap;

import java.util.Random;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/4/10
 *
 * 优先队列 ：
 *   非线性数据结构
 *   出队顺讯和入队顺序无关；和优先级相关
 *
 * 优先队列应用
 *    动态选择优先级最高的任务执行 （任务处理中心）
 *    游戏的自动攻击（进入一定范围之后，找最近的敌人攻击）
 *
 *                           入队           出队（拿出最大元素）
 * 使用普通线性结构实现：       O(1)             O(n)
 * 使用顺序线性结构实现：       O(n)             O(1)
 * 使用堆实现:                O(logn)          O(logn)
 *
 *  经典问题：
 *      在1000000个元素中选出前100名？
 *          在N个元素中选出前M个元素
 *             排序 ？ NlogN
 *            使用优先队列? NlogM
 *         使用优先队列，维护当前看到的前M个元素
 *                需要使用最小堆
 *
 *  堆的种类： 二叉堆，d叉堆，索引堆，二项堆，斐波那契堆
 *
 */
public class PriorityQueue<E extends java.lang.Comparable<E>> implements Queue<E> {


    private MaxHeap<E> data;

    public PriorityQueue(){
         data = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        data.add(e);
    }

    @Override
    public E dequeue() {
        return data.extractMax();
    }

    @Override
    public E getFront() {
        return data.findMax();
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        int n = 10000;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.dequeue();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException();
            }
        }
        System.out.println("Test PriorityQueue completed.");
    }
}
