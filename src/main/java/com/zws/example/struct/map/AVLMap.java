package com.zws.example.struct.map;

import com.zws.example.struct.tree.AVLTree;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/6/10
 */
public class AVLMap<K extends java.lang.Comparable<K>,V> implements Map<K,V> {


    private AVLTree<K,V> data;


    public AVLMap(){
        data = new AVLTree<>();
    }



    @Override
    public void put(K key, V value) {
        data.add(key,value);
    }

    @Override
    public V remove(K key) {
        return  data.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return data.contains(key);
    }

    @Override
    public V get(K key) {
        return data.get(key);
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
