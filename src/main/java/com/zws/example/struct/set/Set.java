package com.zws.example.struct.set;


/**
 *   Set集合
 *     承载元素的容器，但每个元素都只能存在一次；能够快速实现“去重”这个工作；
 *   典型应用：
 *      客户统计
 *      字汇量统计
 * @param <E>
 */
public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
