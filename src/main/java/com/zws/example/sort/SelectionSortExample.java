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

        int number =100000;
        int rangeL = 0;
        int rangeR = 1000;
        int swapTimes = 0;

        /**
         * 最差测试
         */
        Integer[] arrays = SortUtil.generateRandomArray(number,rangeL,rangeR);
        SortUtil.calculateTime("Worst: selection sort",SelectionSortExample::selectionSort,arrays);
       // SortUtil.printArray(arrays);

        /**
         * 最优测试
         */
        arrays = SortUtil.generateNearlyOrderedArray(number, swapTimes);

        SortUtil.calculateTime("Optimal: selection sort",SelectionSortExample::selectionSort,arrays);
       // SortUtil.printArray(arrays);



    }


    /**
     * 选择排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     *
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
     * 代码                                              执行次数
     *  int minIndex = i;                                   n
     *  compareTo                                       (n^2-n)/2
     *  minIndex = j;                             最好：0  最差； (n^2-n)/2
     *  SortUtil.swap(....                            最好：0 最差；3n
     *
     * 求时间复杂度：
     *  根据以上得出时间频度：
     *   最优： T(n) =  0.5n^2+0.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *   最差： T(n) =  n^2+2n 得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *  所以得出时间无论什么情况下复杂度为 O(n^2)
     *
     * @param comparableArray
     */
    private static void selectionSort(Comparable[] comparableArray) {
        for (int i = 0; i < comparableArray.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < comparableArray.length; j++) {

                if (comparableArray[minIndex].compareTo(comparableArray[j]) == 1) {
                    minIndex = j;
                }
            }

            if(i==minIndex){
                continue;
            }
            SortUtil.swap(comparableArray,i,minIndex);

        }
    }






}
