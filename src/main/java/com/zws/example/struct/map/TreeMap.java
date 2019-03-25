package com.zws.example.struct.map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 */
public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {


    private Node root;
    private Integer size;

    class Node {
        private K key;
        private V value;
        private Node left, right;


        public Node() {
            this(null, null);
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node parent = root;
        //Node ;
        while (parent == null) {
            if (parent.key.compareTo(key) == 1) {
                if (parent.left == null) {
                    parent.left = new Node(key, value);
                    size++;
                    return;
                }
                parent = parent.left;
            }
            if (parent.key.compareTo(key) == -1) {
                if (parent.right == null) {
                    parent.right = new Node(key, value);
                    size++;
                    return;
                }
                parent = parent.right;
            }
            if (parent.key.compareTo(key) == 0) {
                parent.value = value;
                return;
            }
        }
    }

    @Override
    public V remove(K key) {

        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
