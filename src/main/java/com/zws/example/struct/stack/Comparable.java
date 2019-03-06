package com.zws.example.struct.stack;

import com.zws.util.CalculateTimeUtil;

import java.util.Random;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 *
 *
 * 总结：
 *    一：数组栈比链表栈出栈慢
 *        但是数组入栈是如果长度不够会一次开出两陪的空间
 *        而链表是每次入栈都会开辟空间，所以导致数组的入栈操作比链表快 n够大是整体的性能低于数组栈
 *
 *
 *
 *
 */
public class Comparable {

    public static void main(String[] args) {
        int n = 1000000;

        double time1 = CalculateTimeUtil.calculateTime(Comparable::testArrayStack, n);
        System.out.println("ArrayStack, time: " + time1 + " s");

        double time2 = CalculateTimeUtil.calculateTime(Comparable::testLinkedListStack, n);
        System.out.println("LinkedListStack, time: " + time2 + " s");

    }

    public static void testArrayStack(int n){
        ArrayStack stack = new ArrayStack();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < n ; i ++)
            stack.pop();
    }

    public static void testLinkedListStack(int n){

        LinkedListStack stack = new LinkedListStack();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < n ; i ++)
            stack.pop();
    }
}
