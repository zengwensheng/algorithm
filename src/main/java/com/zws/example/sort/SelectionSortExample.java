package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/14
 *
 *
 *
 * 选择排序:
 *     从数组中选择一个最小的数字，将这个值放到数组的第一个
 *
 */
public class SelectionSortExample {


    public static void main(String[] args) {

        int number =1000;
        int rangeL = 0;
        int rangeR = 1000;

        Integer[] arrays = SortUtil.generateRandomArray(number,rangeL,rangeR);
        SortUtil.calculateTime("selection sort",SelectionSortExample::selectionSort,arrays);
        SortUtil.printArray(arrays);



    }


    /**
     * 选择排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     * 求时间复杂度：
     *  根据一下得出时间频度：
     *   最优： T(n) =  n^2+3n+2  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *   最差： T(n) =  1.5n^2+4.5n+2 得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *  所以得出时间复杂度为 O(n^2)
     *
     * @param comparableArray
     */
    private static void selectionSort(Comparable[] comparableArray) {
        /**
         *  n + 1 次
         */
        for (int i = 0; i < comparableArray.length; i++) {
            /**
             * n 次
             */
            int minIndex = i;
            /**
             * 等差数列：
             *   是指从第二项起，每一项与它的前一项的差等于同一个常数的一种数列，常用A、P表示。这个常数叫做等差数列的公差，公差常用字母d表示。
             *   例如：1,3,5,7,9……2n-1。
             *   通项公式为：an=a1+(n-1)*d。首项a1=1，公差d=2。
             *   前n项和公式为：Sn=a1*n+[n*(n-1)*d]/2或Sn=[n*(a1+an)]/2。注意：以上n均属于正整数。
             *
             *
             * 第一次：n-1 第二次  n-2 .... 最后一次 1
             * 等差数列求和得： (1+n-1)*(n-1)/2
             *
             * (n^2-n)/2+1
             */
            for (int j = i + 1; j < comparableArray.length; j++) {
                /**
                 *  (n^2-n)/2
                 */
                if (comparableArray[minIndex].compareTo(comparableArray[j]) == 1) {
                    /**
                     * 这个不太确定
                     * 最优: 0
                     * 最差: (n^2-n)/2
                     */
                    minIndex = j;
                }
            }
            /**
             * 这个不太确定
             * 最优: 2n
             * 最差: 4n
             */
            if(i==minIndex){
                continue;
            }
            SortUtil.swap(comparableArray,i,minIndex);

        }
    }






}
