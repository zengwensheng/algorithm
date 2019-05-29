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


    private int balanceFactor =1;

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
     * @return
     */
    public boolean isBST(){
        Array<K> array =new Array<>();
        inOrder(root,array);
        for(int i= 1;i<array.getSize();i++){
             if(array.get(i).compareTo(array.get(i-1))==-1){
                  return false;
             }
        }
        return true;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
         if(node==null){
             return true;
         }
         if(Math.abs(getBalanceFactor(node))>balanceFactor){
             return false;
         }
         return isBalanced(node.left)&isBalanced(node.right);
    }

    private void inOrder(Node node,Array<K> array){
        if(node==null){
            return;
        }

        Stack<Node> stack = new ArrayStack<>();
        stack.push(node);

        while(stack.isEmpty()){
            if(stack.peek().left!=null){
                stack.push(stack.peek().left);
                continue;
            }
            Node cur = stack.pop();
            array.addLast(cur.key);
            if(cur.right!=null){
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

        //计算平衡
        Integer balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }


        return node;
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


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.key);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        //preOrder(root);
    }

    // 递归前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (root == null) {
            return;
        }


        Stack<Node> stack = new ArrayStack<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {

            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.println(cur.key);
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = null;
            }


        }


        // inOrder(root);
    }

    //递归中序遍历
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);

    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            return;
        }

        Stack<Node> nodeStack = new ArrayStack<>();
        nodeStack.push(root);
        Array<Node> nodeArray = new Array<>();

        while (!nodeStack.isEmpty()) {

            Node cur = nodeStack.pop();

            if (nodeArray.contains(cur)) {
                System.out.println(cur.key);
                continue;
            }
            if (cur.left == null && cur.right == null) {
                System.out.println(cur.key);
                continue;
            }
            nodeStack.push(cur);
            nodeArray.addLast(cur);

            if (cur.right != null) {
                nodeStack.push(cur.right);
            }

            if (cur.left != null) {
                nodeStack.push(cur.left);
            }

        }




        /*postOrder(root);*/
    }

    // 递归后序遍历
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodeQueue = new LinkedListQueue<>();
        nodeQueue.enqueue(root);
        while (!nodeQueue.isEmpty()) {
            Node cur = nodeQueue.dequeue();
            System.out.println(cur.key);
            if (cur.left != null) {
                nodeQueue.enqueue(cur.left);
            }
            if (cur.right != null) {
                nodeQueue.enqueue(cur.right);
            }

        }

    }


    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public K removeMin() {
        Node ret = minimum(root);
        removeMin(root);
        return ret.key;

    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;

    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node e) {
        while (e.left != null) {
            e = e.left;
        }
        return e;
    }


    // 从二分搜索树中删除最大值所在节点
    public K removeMax() {
        Node del = maximum(root);
        root = removeMax(root);
        return del.key;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
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
        if (node.key.compareTo(e) == 0) {

            //待删除元素左子树为空的情况下
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            //待删除元素右子树为空的情况下
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

            //待删除元素左，右子树都不为空的情况下，找到右子树中最小元素或左子树最大元素代替当前元素
            Node successor = minimum(node.right);
            successor.right = remove(successor.key, node.right);
            successor.left = node.left;
            return successor;
        }

        if (node.key.compareTo(e) > 0) {
            node.left = remove(e, node.left);
        } else if (node.key.compareTo(e) < 0) {
            node.right = remove(e, node.right);
        }

        return node;
    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
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
            System.out.println("is BST : " + map.isBST() );
            System.out.println("is Balanced : "+map.isBalanced());
        }
        System.out.println();
    }




}
