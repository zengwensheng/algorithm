package com.zws.example.struct.set;


/**
 *   Set集合
 *     承载元素的容器，但每个元素都只能存在一次；能够快速实现“去重”这个工作；
 *   典型应用：
 *      客户统计
 *      字汇量统计
 *
 *   有序集合：集合中的元素具有顺序性
 *   无序集合：集合中的元素没有顺序性
 *   多重集合：集合中的元素可以重复
 * @param <E>
 */
public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
