package com.zws.example.struct.map;

import com.zws.example.struct.base.Array;
import com.zws.util.FileUtil;


/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 *
 * 基于二分搜索树完成的映射，有序映射
 *
 */
public class BSTMap<K extends java.lang.Comparable<K>, V> implements Map<K, V> {


    private Node root;
    private Integer size=0;

    public BSTMap(){

    }

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
        while (parent != null) {
            if (parent.key.compareTo(key) >0) {
                if (parent.left == null) {
                    parent.left = new Node(key, value);
                    size++;
                    return;
                }
                parent = parent.left;
            }
            if (parent.key.compareTo(key) < 0) {
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

        remove(key, root);
        return get(key);
    }


    private Node remove(K key, Node n) {
        if (n == null) {
            return n;
        }

        if (n.key.compareTo(key) == 1) {
            n.left = remove(key, n.left);
        }
        if (n.key.compareTo(key) == -1) {
            n.right = remove(key, n.right);
        }

        if (n.key.compareTo(key) == 0) {

            if (n.right == null) {
                Node left = n.left;
                n.left = null;
                size--;
                return left;
            }

            if (n.left == null) {
                Node right = n.right;
                n.right = null;
                size--;
                return right;
            }

            Node successor = maximum(n.left);
            successor.left = remove(successor.key,n.left);
            successor.right = n.right;
        }
        return n;


    }


    private Node maximum(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    @Override
    public boolean contains(K key) {
        Node cur = root;
        while(cur!=null){
            if(cur.key.compareTo(key)>0){
                cur = cur.left;
                continue;
            }
            if(cur.key.compareTo(key)<0){
                cur = cur.right;
                continue;
            }
            if(cur.key.compareTo(key)==0){
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        Node cur = root;
        while(cur!=null){
            if(cur.key.compareTo(key)>0){
                cur = cur.left;
            }
            if(cur.key.compareTo(key)<0){
                cur = cur.right;
            }
            if(cur.key.compareTo(key)==0){
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
        return size==0;
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Pride and Prejudice");
        Array<String> words = new Array<>();
        if (FileUtil.readFile(LinkedListMap.class.getResource("/").getFile() + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.getSize());
            BSTMap<String, Integer> map = new BSTMap<>();
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
