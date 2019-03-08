package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.stack.ArrayStack;
import com.zws.example.struct.stack.Stack;

import java.util.HashMap;

/**
 *
 * 特点:
 *    动态数据结构,是一颗二叉树
 *    二分搜索树的每个节点的值:
 *       每个节点的值都大于其左子树的所有节点的值
 *       每个节点的值都小于其右子树的所有节点的值
 *
 * 缺点：
 *     动态数据
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/6
 */
public class BinarySearchTree<E extends Comparable<E>> {
    class Node {
        E e;
        Node left, right;

        public Node(E e, Node right, Node left) {
            this.e = e;
            this.right = right;
            this.left = left;
        }

        public Node(E e) {
            this(e, null, null);
        }
    }

    private Node root;

    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        //root = add(root, e);
        Node cur = root;
        while (true) {
            if (cur == null) {
                root = new Node(e);
                break;
            }

            if (cur.e.compareTo(e) == 1) {
                if (cur.left == null) {
                    cur.left = new Node(e);
                    break;
                }
                cur = cur.left;

            } else {
                if (cur.right == null) {
                    cur.right = new Node(e);
                    break;
                }
                cur = cur.right;
            }
        }


    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) == 1) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }
        return node;
    }

    public Boolean contains(E e) {
        Node cur = root;
        while (cur != null) {

            if (cur.e.equals(e)) {
                return true;
            }

            if (cur.e.compareTo(e) == 1) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;


        //return contains(root, e);
    }

    private Boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        if (node.e.compareTo(e) == 1) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }


    public void preOrder() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        //preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);

    }


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
            System.out.println(cur.e);
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = null;
            }


        }


        // inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }

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
                System.out.println(cur.e);
                continue;
            }
            if (cur.left == null && cur.right == null) {
                System.out.println(cur.e);
                continue;
            }
            nodeStack.push(cur);
            nodeArray.addLast(cur);

            if (cur.right != null) {
                nodeStack.push(cur.right);
            }

            if(cur.left != null){
                nodeStack.push(cur.left);
            }

        }




        /*postOrder(root);*/
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateString(root, 0, res);
        return res.toString();

    }


    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left, depth + 1, res);
        generateString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }


    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 8, 6, 4, 2};
        for (int num : nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3     8   //
        //  / \   /    //
        // 2  4  6     //
        /////////////////
        bst.preOrder();
        System.out.println();

        bst.inOrder();
        System.out.println();

        bst.postOrder();
        System.out.println();


    }


}
