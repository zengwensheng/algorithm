package com.zws.example.struct.base;

/**
 * 链表：
 * 正的动态数据结构，不需要处理固定容量的问题，但是丧失了随机访问的能力
 * <p>
 * 数组和链表的对比：
 * <p>
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
        E e;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }


    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 时间复杂度： O(1)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 时间复杂度： O(n)
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 时间复杂度： O(1)
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 时间复杂度： O(n)
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node del = prev.next;
        prev.next = del.next;
        del.next = null;
        size--;
        return del.e;

    }

    /**
     * 时间复杂度： O(1)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 时间复杂度： O(n)
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 时间复杂度： O(n)
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }
        if(prev!=null){
            Node del = prev.next;
            prev.next = del.next;
            del.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }


    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }



}
