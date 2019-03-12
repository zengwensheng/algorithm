package com.zws.example.struct.queue;

/**
 *  队列
 *   特点：
 *     特殊的线性表，只能从一段（队尾）添加元素，只能从别一端（队首）取出元素
 * <p>
 * 先进先出 First In First Out(FIFO)
 */
public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();

}
