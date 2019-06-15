package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;
import com.zws.util.FileUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/6/10
 * <p>
 * 红黑树  是一种自平衡二叉查找树
 * <p>
 * 它是在1972年由Rudolf Bayer发明的，当时被称为平衡二叉B树（symmetric binary B-trees）。后来，在1978年被 Leo J. Guibas 和 Robert Sedgewick 修改为如今的“红黑树”。
 * <p>
 * 等价与23树
 * <p>
 * 性质：
 * 节点是红色或黑色。
 * 根节点是黑色
 * 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 */
public class RBTree<K extends Comparable<K>, V> {

    private final boolean RED = true;
    private final boolean BLOCK = false;


    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }


    private Node root;

    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLOCK;
        }
        return node.color;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public boolean contains(K key) {
        return get(root, key) != null;
    }

    public V get(K key) {
        Node node = get(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            return get(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    public void set(K key, V value) {
        Node node = get(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key+" doesn't exists");
        }
        node.value = value;
    }

    public V remove(K key) {
        Node ret = get(root, key);
        if (ret != null) {
            root = remove(root, key);
            return ret.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode;

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {

            if (node.left == null) {
                retNode = node.right;
                node.right = null;
                size--;

            } else if (node.right == null) {
                retNode = node.left;
                node.left = null;
                size--;

            } else {
                Node successor = minimum(node.right);
                successor.right=  remove(node.right,successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;


            }
        }
        return  retNode;
    }

    private Node minimum(Node node){
        if(node.left !=null){
            return minimum(node.left);
        }
        return node;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        Array<String> words = new Array<>();
        if (FileUtil.readFile(AVLTree.class.getResource("/").getFile() + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words:" + words.getSize());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.add(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("Frequency of PRIDE:" + map.get("pride"));
            System.out.println("Frequency of PREJUDICE:" + map.get("prejudice"));


            for(String word: words){
                map.remove(word);
            }
            if(map.getSize()!=0){
                throw  new IllegalArgumentException();
            }

        }
        System.out.println();



    }

}
