package com.zws.example.struct.set;

import com.zws.util.FileUtil;

import javax.print.DocFlavor;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/**
 * 总结：
 *   二分搜索树集合总体比链表要快
 *
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 */
public class Comparable {


    private static double testSet(Set<String> set, String filename) {
        Instant start = Instant.now();
        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();
        if (FileUtil.readFile(filename, words)) {

            System.out.println("Total words:" + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words:" + set.getSize());

        }
        return Duration.between(start,Instant.now()).toMillis();
    }


    public static void main(String[] args) {

        String filename = "/Users/zws/IdeaProjects/algorithm/src/main/resources/pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time1 + " ms");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " ms");
    }
}
