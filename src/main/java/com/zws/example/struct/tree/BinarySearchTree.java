package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.queue.LinkedListQueue;
import com.zws.example.struct.queue.Queue;
import com.zws.example.struct.stack.ArrayStack;
import com.zws.example.struct.stack.Stack;
import com.zws.util.SortUtil;

import java.util.ArrayList;
import java.util.Random;


/**
 * 特点:
 * 动态数据结构,是一颗二叉树
 * 二分搜索树的每个节点的值:
 * 每个节点的值都大于其左子树的所有节点的值
 * 每个节点的值都小于其右子树的所有节点的值
 * 每一棵子树也是二分搜索树
 * <p>
 * 缺点：
 * 在一些情况下二叉树会退化成链表 （如从小到大依次插入)
 * <p>
 * <p>
 * 遍历分类
 * <p>
 * 深度优先遍历 :
 * <p>
 * 前序遍历 : 对当前节点的遍历在对左右孩子节点的遍历之前, 遍历顺序 : 当前节点->左孩子->右孩子
 * 中序遍历 : 对当前节点的遍历在对左右孩子节点的遍历中间, 遍历顺序 : 左孩子->当前节点->右孩子
 * 后序遍历 : 对当前节点的遍历在对左右孩子节点的遍历之后, 遍历顺序 : 左孩子->右孩子->当前节点
 * 广度优先遍历 :
 * <p>
 * 层序遍历 : 按层从左到右进行遍历
 * <p>
 * <p>
 * 如果该二分搜索树中需要存在重复元素，请将相等的两个元素的comparable的比较的值改为不等于0就行了
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

    /**
     * 在这个树是平衡的情况下
     * 时间复杂度：O(h) = O(logn)
     */
    public void add(E e) {
        //root = add(root, e);
        Node cur = root;
        while (true) {
            if (cur == null) {
                root = new Node(e);
                size++;
                break;
            }

            if (cur.e.compareTo(e) > 0) {
                if (cur.left == null) {
                    cur.left = new Node(e);
                    size++;
                    break;
                }
                cur = cur.left;

            } else if (cur.e.compareTo(e) < 0) {
                if (cur.right == null) {
                    cur.right = new Node(e);
                    size++;
                    break;
                }
                cur = cur.right;
            } else {
                break;
            }
        }


    }

    // 递归添加元素
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 在这个树是平衡的情况下
     * 时间复杂度：O(h) = O(logn)
     */
    public Boolean contains(E e) {
        Node cur = root;
        while (cur != null) {

            if (cur.e.equals(e)) {
                return true;
            }

            if (cur.e.compareTo(e) > 0) {
                cur = cur.left;
            } else if (cur.e.compareTo(e) < 0) {
                cur = cur.right;
            }
        }
        return false;


        //return contains(root, e);
    }

    // 递归查询元素
    private Boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (node.e.equals(e)) {
            return true;
        }

        if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            return contains(node.right, e);
        }
        return false;
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

    // 递归前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
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
            System.out.println(cur.e);
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
        System.out.println(node.e);
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
        System.out.println(node.e);
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
            System.out.println(cur.e);
            if (cur.left != null) {
                nodeQueue.enqueue(cur.left);
            }
            if (cur.right != null) {
                nodeQueue.enqueue(cur.right);
            }

        }

    }


    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {
        Node ret = minimum(root);
        removeMin(root);
        return ret.e;

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
    public E removeMax() {
        Node del = maximum(root);
        root = removeMax(root);
        return del.e;
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
    public void remove(E e) {
        root = remove(e, root);
    }

    // 删除掉以node节点为根的二分搜索树中值为e的节点
    // 返回删除节点后新的二分搜索树的根
    private Node remove(E e, Node node) {
        if (node == null) {
            return node;
        }
        if (node.e.equals(e)) {

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
            successor.right = remove(successor.e, node.right);
            successor.left = node.left;
            return successor;
        }

        if (node.e.compareTo(e) > 0) {
            node.left = remove(e, node.left);
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(e, node.right);
        }

        return node;
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

       /* BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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

        bst.levelOrder();
        System.out.println();*/

       /* BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");*/

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();

        int n = 1000;

        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(n));
        }

        Integer[] order = SortUtil.generateNearlyOrderedArray(n, n);

        for (int i = 0; i < n; i++) {
            if(bst.contains(order[i])){
                bst.remove(order[i]);
            }
        }

        System.out.println(bst.size());


    }


}
