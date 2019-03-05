package com.zws.example.struct.base;

/**
 * 链表：
 * 真正的动态数据结构，不需要处理固定容量的问题，但是丧失了随机访问的能力
 * <p>
 * 数组和链表的对比：
 * 数组最好用于索引有语意的情况
 * 最大的优点：支持快速查询
 * <p>
 * 链表不适合用于索引有语意的情况
 * 最大的优点： 动态，节省空间
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 */
public class LinkedList<E> {

    private class Node {
        E val;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.val = val;
            this.next = next;
        }

    }


    private Node head;
    private int size;

}
