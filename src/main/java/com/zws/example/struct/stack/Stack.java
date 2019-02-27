package com.zws.example.struct.stack;

public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 入栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈顶 第一个元素
     * @return
     */
    E peek();

}
