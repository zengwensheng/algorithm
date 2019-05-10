package com.zws.example.struct.tree;


/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/9
 * <p>
 * AVL树 ， 最先发明的自平衡二叉查找树
 * 发明者：G. M. Adelson-Velsky和E. M. Landis，他们在1962年的论文《An algorithm for the organization of information》中发表了它
 * <p>
 * 特点：
 * 本身首先是一颗二叉树
 * 带有平衡条件： 每个节点的左右紫薯的高度之差的绝对值（平衡因子） 最多为1  （对于任意一个节点，左子树和右子树的高度差不能为超过1）
 * 也就是说，AVL树，本质上是带了平衡功能的二叉查找树（二叉排序树，二叉搜索树）。
 */
public class AVLTree<K extends Comparable<K>, V> {

    class Node {
        K key;
        V value;
        Node left, right;
        Integer height;

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        Node(K key, V value) {
            this(key, value, null, null);
        }
    }


    private Node root;

    private Integer size;


    public AVLTree() {
        root = null;
        size = 0;
    }


    public int size()


    public boolean isEmpty()


    public void add(E e)


    public Boolean contains(E e)


}
