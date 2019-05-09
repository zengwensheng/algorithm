package com.zws.example.struct.tree.uf;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/8
 *
 *
 * QuickFind   最慢
 *
 * QuickUnion  快于QuickFind ，缺点：树太高，回增加复杂度
 *
 * QuickUnion1 快于QuickUnion  缓解了树的高度：将 子节点少的树 连接到 子节点多的树
 *
 *
 * 并查集时间复杂度分析  O(logn)
 *     严格意义上 ： O(long n)    ---->   iterated logarithm
 *
 *                  0        if(n<= 1)
 *     logn = {
 *              1+log(logn)  if(n>1)
 *      近乎是O(1)级别的
 *
 */
public class Comparable {

    private static long testUF(UF uf,int m){

        Integer size = uf.getSize();
        Random random = new Random();
        Instant start = Instant.now();

        for(int i = 0 ; i< m ;i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        for(int i =0;i<m ; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        return Duration.between(start,Instant.now()).toMillis();
    }

    public static void main(String[] args) {

        int size = 100000;
        int m = 100000;
        UF uf = null;
        /*uf = new QuickFind(size);
        System.out.println("QuickFind :"+testUF(uf,m)+"ms");
        uf = new QuickUnion(size);
        System.out.println("QuickUnion :"+testUF(uf,m)+"ms");*/
        uf = new QuickUnion1(size);
        System.out.println("QuickUnion1 :"+testUF(uf,m)+"ms");
        uf = new QuickUnion2(size);
        System.out.println("QuickUnion2 :"+testUF(uf,m)+"ms");
        uf = new QuickUnion3(size);
        System.out.println("QuickUnion3 :"+testUF(uf,m)+"ms");
        uf = new QuickUnion4(size);
        System.out.println("QuickUnion4 :"+testUF(uf,m)+"ms");

    }
}
