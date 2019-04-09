package com.zws.example.struct.map;


import com.zws.example.struct.base.Array;
import com.zws.util.FileUtil;

import java.util.ArrayList;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 *
 * 基于链表完成的映射，有序映射
 *
 * 算法复杂度 {@link Comparable}
 */
public class LinkedListMap<K, V> implements Map<K, V> {


    private Node dummyHead;

    private Integer size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }


    class Node {
        K key;
        V value;
        Node next;

        public Node() {
            this(null, null, null);
        }


        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    @Override
    public void put(K key, V value) {

        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        Node previous = dummyHead;
        while (previous.next != null) {
            if (previous.next.key.equals(key)) {
                previous.next.value = value;
                return;
            }
            previous = previous.next;
        }
        previous.next = new Node(key, value);
        size++;
    }

    @Override
    public V remove(K key) {
        Node previous = dummyHead;
        while (previous.next != null) {
            if (previous.next.key.equals(key)) {
                Node del = previous.next;
                previous.next = del.next;
                del.next = null;
                size--;
                return del.value;
            }
            previous = previous.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public V get(K key) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
        }
        return null;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Pride and Prejudice");
        Array<String> words = new Array<>();
        if (FileUtil.readFile(LinkedListMap.class.getResource("/").getFile() + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.getSize());
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                  if(map.contains(word)){
                      map.put(word,map.get(word)+1);
                  }else{
                      map.put(word,1);
                  }
            }
            System.out.println("Total different words:"+map.getSize());
            System.out.println("Frequency of PRIDE:"+map.get("prejudice"));
            System.out.println("Frequency of PREJUDICE:"+map.get("prejudice"));
        }
        System.out.println();
    }
}
