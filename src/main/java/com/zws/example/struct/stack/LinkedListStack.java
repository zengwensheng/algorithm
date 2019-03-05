package com.zws.example.struct.stack;

import com.zws.example.struct.base.LinkedList;

/**
 *
 * 用链表是实现栈
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> data;

    public LinkedListStack(){
        data = new LinkedList<>();
    }


    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 时间复杂度： O（1）
     */
    @Override
    public void push(E e) {
          data.addFirst(e);
    }

    /**
     * 时间复杂度： O（1）
     */
    @Override
    public E pop() {
        return data.removeFirst();
    }

    /**
     * 时间复杂度： O（1）
     */
    @Override
    public E peek() {
        return data.getFirst();
    }
}
