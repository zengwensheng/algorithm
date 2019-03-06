package com.zws.example.struct.tree;

/**
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
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) == 1) {
            node.right = add(node.right, e);
        } else {
            node.left = add(node.left, e);
        }
        return node;
    }

    public Boolean contains(E e) {
        return contains(root, e);
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
        preOrder(root);
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
        inOrder(root);
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
        postOrder(root);
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
        return "";
    }

}
