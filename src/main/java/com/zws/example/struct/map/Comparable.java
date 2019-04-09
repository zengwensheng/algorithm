package com.zws.example.struct.map;

import com.zws.example.struct.base.Array;
import com.zws.util.FileUtil;

import java.time.Duration;
import java.time.Instant;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/4/4
 *
 *                      LinkedListMap(平均 )    BSTMap      平均     最差
 *  增 add                 O(n)                 O(h)    O(logn)    O(n)
 *  删 remove              O(n)                 O(h)    O(logn)    O(n)
 *  改 set                 O(n)                 O(h)    O(logn)    O(n)
 *  查 get                 O(n)                 O(h)    O(logn)    O(n)
 *  查 contains            O(n)                 O(h)    O(logn)    O(n)
 *
 *
 *
 */
public class Comparable {

    private static Long testMap(Map<String,Integer> map,String filename){
        Instant start = Instant.now();
        System.out.println(filename);
        Array<String> words = new Array<>();
        if(FileUtil.readFile(LinkedListMap.class.getResource("/").getFile()+filename,words)){
            System.out.println("Total words: "+words.getSize());

            for(String word:words){
                if(map.contains(word)){
                    map.put(word,map.get(word)+1);
                }else{
                    map.put(word,1);
                }
            }
            System.out.println("Total different words:"+map.getSize());
            System.out.println("Frequency of PRIDE:"+map.get("pride"));
            System.out.println("Frequency of PREJUDICE:"+map.get("prejudice"));
        }
        return Duration.between(start,Instant.now()).toMillis();
    }
    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";

        BSTMap<String,Integer> bstMap = new BSTMap();
        Long time1 = testMap(bstMap,filename);
        System.out.println("BST Map: " + time1 + " ms");

        System.out.println();

        LinkedListMap<String,Integer> linkedListMap = new LinkedListMap<>();
        Long time2 = testMap(linkedListMap,filename);
        System.out.println("Linked List Map: " + time2 + " ms");

    }
}
