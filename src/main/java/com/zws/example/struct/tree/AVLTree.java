package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.queue.LinkedListQueue;
import com.zws.example.struct.queue.LoopQueue;
import com.zws.example.struct.queue.Queue;
import com.zws.example.struct.stack.ArrayStack;
import com.zws.example.struct.stack.Stack;
import com.zws.util.FileUtil;
import com.zws.util.SortUtil;

import java.util.ArrayList;
import java.util.Random;


/**
 * <p>
 * AVL树 ， 最先发明的自平衡二叉查找树
 * 发明者：G. M. Adelson-Velsky和E. M. Landis，他们在1962年的论文《An algorithm for the organization of information》中发表了它
 * <p>
 * 特点：
 * 本身首先是一颗二叉树
 * 带有平衡条件： 每个节点的左右紫薯的高度之差的绝对值（平衡因子） 最多为1  （对于任意一个节点，左子树和右子树的高度差不能为超过1）
 * 也就是说，AVL树，本质上是带了平衡功能的二叉查找树（二叉排序树，二叉搜索树）。
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/6
 */
public class AVLTree<K extends Comparable<K>, V> {
    class Node {
        K key;
        V value;
        Node left, right;
        Integer height;

        public Node(K key, V value, Node right, Node left) {
            this.key = key;
            this.value = value;
            this.right = right;
            this.left = left;
            this.height = 1;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    private Node root;

    private int size;


    private int balanceFactor = 1;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断该二叉树是否是一个二分搜素树
     *
     * @return
     */
    public boolean isBST() {
        Array<K> array = new Array<>();
        inOrder(root, array);
        for (int i = 1; i < array.getSize(); i++) {
            if (array.get(i).compareTo(array.get(i - 1)) == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > balanceFactor) {
            return false;
        }
        return isBalanced(node.left) & isBalanced(node.right);
    }

    private void inOrder(Node node, Array<K> array) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new ArrayStack<>();
        stack.push(node);

        while (stack.isEmpty()) {
            if (stack.peek().left != null) {
                stack.push(stack.peek().left);
                continue;
            }
            Node cur = stack.pop();
            array.addLast(cur.key);
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }


    }

    /**
     * 在这个树是平衡的情况下
     * 时间复杂度：O(h) = O(logn)
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 递归添加元素
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

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子
        Integer balanceFactor = getBalanceFactor(node);

        if(Math.abs(balanceFactor)<=this.balanceFactor){
            return node;
        }

        // 维持树平衡

        // LL
        if (balanceFactor > 0 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // LR
        if (balanceFactor > 0  && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RR
        if (balanceFactor < 0 && getBalanceFactor(node.right) < 0) {
            return leftRotate(node);
        }

        //RR
        if (balanceFactor < 0 && getBalanceFactor(node.right) >= 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    // 对节点x进行向右旋转操作，返回旋转后新的根节点y
    //        x                              y
    //       / \                           /   \
    //      y   T4     向右旋转 (y)        z     x
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node x) {
        Node y = x.left;

        Node t3 = y.right;
        y.right = x;
        x.left = t3;

        //更新height
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // 对节点x进行向左旋转操作，返回旋转后新的根节点y
    //    x                             y
    //  /  \                          /   \
    // T1   y      向左旋转 (y)       x     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    private Node leftRotate(Node x) {
        Node y = x.right;

        Node t2 = y.left;
        y.left = x;
        x.right = t2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;

    }


    private Integer getHeight(Node e) {
        if (e == null) {
            return 0;
        }
        return e.height;
    }

    private Integer getBalanceFactor(Node e) {
        return getHeight(e.left) - getHeight(e.right);
    }

    /**
     * 在这个树是平衡的情况下
     * 时间复杂度：O(h) = O(logn)
     */
    public Boolean contains(K key) {
        return contains(root, key);
    }

    // 递归查询元素
    private Boolean contains(Node node, K key) {

        if (node == null) {
            return false;
        }

        if (node.key.compareTo(key) == 0) {
            return true;
        }

        if (node.key.compareTo(key) > 0) {
            return contains(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return contains(node.right, key);
        }
        return false;
    }


    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            return getNode(node.left, key);
        }
        if (node.key.compareTo(key) < 0) {
            return getNode(node.right, key);
        }
        return null;
    }




    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node e) {
        while (e.left != null) {
            e = e.left;
        }
        return e;
    }



    //从二分搜索树中删除元素为e的节点
    public void remove(K e) {
        root = remove(e, root);
    }

    // 删除掉以node节点为根的二分搜索树中值为e的节点
    // 返回删除节点后新的二分搜索树的根
    private Node remove(K e, Node node) {
        if (node == null) {
            return node;
        }

         Node res = null;

        if (node.key.compareTo(e) == 0) {

            //待删除元素左子树为空的情况下
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                res = right;
            }else
            //待删除元素右子树为空的情况下
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                res  = left;
            }else {

                //待删除元素左，右子树都不为空的情况下，找到右子树中最小元素或左子树最大元素代替当前元素
                Node successor = minimum(node.right);
                successor.right = remove(successor.key, node.right);
                successor.left = node.left;
                res  = successor;
            }
        }

        if (node.key.compareTo(e) > 0) {
            node.left = remove(e, node.left);
            res = node;
        } else if (node.key.compareTo(e) < 0) {
            node.right = remove(e, node.right);
            res = node;
        }

        if(res==null){
            return res;
        }

        res.height = Math.max(getHeight(res.left), getHeight(res.right)) + 1;

        //计算平衡因子
        Integer balanceFactor = getBalanceFactor(res);

        if(Math.abs(balanceFactor)<=this.balanceFactor){
            return res;
        }

        // 维持树平衡

        // LL
        if (balanceFactor > 0 && getBalanceFactor(res.left) >= 0) {
            return rightRotate(res);
        }

        // LR
        if (balanceFactor > 0  && getBalanceFactor(res.left) < 0) {
            res.left = leftRotate(res.left);
            return rightRotate(res);
        }

        //RR
        if (balanceFactor < 0 && getBalanceFactor(res.right) < 0) {
            return leftRotate(res);
        }

        //RR
        if (balanceFactor < 0 && getBalanceFactor(res.right) >= 0) {
            res.right = rightRotate(res.right);
            return leftRotate(res);
        }


        return res;
    }


    public static void main(String[] args) {
        /*System.out.println("Pride and Prejudice");
        Array<String> words = new Array<>();
        if (FileUtil.readFile(AVLTree.class.getResource("/").getFile() + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words:" + words.getSize());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.add(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words:" + map.size);
            System.out.println("Frequency of PRIDE:" + map.get("pride"));
            System.out.println("Frequency of PREJUDICE:" + map.get("prejudice"));
            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for (String word : words) {
                  map.remove(word);
                  if(!map.isBalanced()){
                      throw  new IllegalArgumentException();
                  }
            }


        }
        System.out.println();*/


        AVLTree<Integer,Integer> tree = new AVLTree<>();
        int n = 10000;
        for(int i = 1;i<n ;i++){
            tree.add(i,0);
        }

        for(int i = n;i>0;i--){
            tree.remove(i);
            if(!tree.isBalanced()){
                throw  new IllegalArgumentException();
            }
        }
    }


}
