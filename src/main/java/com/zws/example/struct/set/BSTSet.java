package com.zws.example.struct.set;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.tree.BinarySearchTree;
import com.zws.util.FileUtil;
import java.util.ArrayList;

/**
 *
 * 集合一种实现(有序集合)：
 *   二分搜索树不能添加重复元素，是非常好的实现“集合”的底层数据结构
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/12
 */
public class BSTSet<E extends java.lang.Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> bst;

    public BSTSet(){
        bst = new BinarySearchTree();
    }

    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }
    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }
    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public void remove(E e) {
         bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        Array<String> words1 = new Array<>();
        if(FileUtil.readFile("/Users/zws/IdeaProjects/algorithm/src/main/resources/pride-and-prejudice.txt",words1)){
            System.out.println("Total words:"+words1.getSize());
            BSTSet<String> set1 = new BSTSet();
            for(String word:words1){
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }
        System.out.println();

        System.out.println("A Tale of Two Cities");

        words1 = new Array<>();
        if(FileUtil.readFile("/Users/zws/IdeaProjects/algorithm/src/main/resources/a-tale-of-two-cities.txt",words1)){
            System.out.println("Total words:"+words1.getSize());
            BSTSet<String> set1 = new BSTSet();
            for (String words:words1){
                set1.add(words);
            }
            System.out.println("Total different words："+set1.getSize());
        }
        System.out.println();
    }
}
