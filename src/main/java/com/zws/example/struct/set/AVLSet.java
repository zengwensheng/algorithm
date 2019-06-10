package com.zws.example.struct.set;

import com.zws.example.struct.tree.AVLTree;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/6/10
 */
public class AVLSet<E extends java.lang.Comparable<E>> implements Set<E> {


    private AVLTree<E,Integer> data;


    public AVLSet(){
        data = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        data.add(e,0);
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public void remove(E e) {
        data.remove(e);
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
