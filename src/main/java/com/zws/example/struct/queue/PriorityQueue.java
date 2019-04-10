package com.zws.example.struct.queue;

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
 *
 *
 *
 *
 */
public class PriorityQueue<E> implements Queue<E> {

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
