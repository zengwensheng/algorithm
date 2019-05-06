package com.zws.example.struct.tree;

import com.zws.example.struct.base.Array;
import com.zws.example.struct.map.LinkedListMap;
import com.zws.example.struct.map.Map;
import com.zws.example.struct.set.BSTSet;
import com.zws.util.FileUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/5
 * <p>
 * Trie 前缀树，字典树
 * 实现字典
 * 二分搜素树                                       Trie
 * 如果有n个条目                             查询每个条目的时间复杂度
 * 使用树结构                                和字典中一共有多少条目无关
 * 查询的时间复杂度是O(logn)                  时间复杂度为O(w)
 * 如果有100万个条目（2^20）                  w为查询单次的长度！
 * logn 大约为20                            大多数单次的长度小于10
 */
public class Trie {

    private class Node {
        public Boolean word;
        public TreeMap<Character, Node> next;

        public Node(Boolean word) {
            this.word = word;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    private int size;


    public Trie() {
        root = new Node();
        size = 0;
    }

    // 返回单次的个数
    public int getSize() {
        return size;
    }

    // 想trie中添加一个单次
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Node n = cur.next.get(word.charAt(i));
            if (n == null) {
                n = new Node();
                cur.next.put(word.charAt(i), n);
            }
            cur = n;
        }

        if (!cur.word) {
            cur.word = true;
            size++;
        }
    }

    // 查询单次word是否在Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Node n = cur.next.get(word.charAt(i));
            if (n == null) {
                return false;
            }
            cur = n;
        }
        return cur.word;
    }


    public boolean startsWith(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            Node n = cur.next.get(prefix.charAt(i));
            if (n == null) {
                return false;
            }
            cur = n;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        Array<String> words = new Array<>();
        if (FileUtil.readFile(Trie.class.getResource("/").getFile() + "pride-and-prejudice.txt", words)) {

            Instant start = Instant.now();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }

            for (String word : words) {
                set.contains(word);
            }

            long time = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet:" + time + "ms");

            // ----
            start = Instant.now();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                if (!trie.contains(word)) {
                    throw new IllegalArgumentException();
                }
            }

            time = Duration.between(start,Instant.now()).toMillis();
            System.out.println("Total different words:"+trie.getSize());
            System.out.println("Trie: "+time+"ms");


        }
    }


}
