package com.zws.example.struct.set;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.base.LinkedList;
import com.zws.util.FileUtil;

import java.util.ArrayList;

/**
 *
 * 集合一种实现(有序集合)：
 *     使用链表实现集合
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 */
public class LinkedListSet<E> implements Set<E> {


    private LinkedList<E> linkedList;


    public LinkedListSet() {
        linkedList = new LinkedList();
    }


    /**
     * 添加 时间复杂度: O(n)
     * @param e
     */
    @Override
    public void add(E e) {
        if(!linkedList.contains(e)) {
            linkedList.addFirst(e);
        }

    }

    /**
     * 是否存在  时间复杂度：O(n)
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    /**
     * 删除 时间复杂度： O(n)
     * @param e
     */
    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        Array<String> words1 = new Array<>();
        if(FileUtil.readFile("/Users/zws/IdeaProjects/algorithm/src/main/resources/pride-and-prejudice.txt",words1)){
            System.out.println("Total words:"+words1.getSize());
            LinkedListSet<String> set1 = new LinkedListSet<>();
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
            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String words:words1){
                set1.add(words);
            }
            System.out.println("Total different words："+set1.getSize());
        }
        System.out.println();
    }
}
