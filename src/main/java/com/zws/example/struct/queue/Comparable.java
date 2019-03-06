package com.zws.example.struct.queue;

import com.zws.util.CalculateTimeUtil;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 *
 * 总结：
 *   一： 循环队列比数组队列的出队速度快
 *        循坏队列实现复杂，浪费一个空间
 *
 *
 *
 */
public class Comparable {

    public static void main(String[] args) {
        int n = 100000;

        double time1 = CalculateTimeUtil.calculateTime(Comparable::testArrayQueue, n);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        double time2 = CalculateTimeUtil.calculateTime(Comparable::testLoopQueue, n);
        System.out.println("LoopQueue, time: " + time2 + " s");

        double time3 = CalculateTimeUtil.calculateTime(Comparable::testLinkedListQueue, n);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }


    public static void testArrayQueue(Integer n) {
        ArrayQueue queue = new ArrayQueue(n);
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }
    }

    public static void testLinkedListQueue(Integer n) {
        LinkedListQueue queue = new LinkedListQueue();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }
    }

    public static void testLoopQueue(Integer n) {
        LoopQueue queue = new LoopQueue(n);
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }
    }
}
