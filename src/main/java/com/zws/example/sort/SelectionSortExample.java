package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/14
 *
 * 等差数列是指从第二项起，每一项与它的前一项的差等于同一个常数的一种数列，常用A、P表示。这个常数叫做等差数列的公差，公差常用字母d表示。
 * 例如：1,3,5,7,9……2n-1。通项公式为：an=a1+(n-1)*d。首项a1=1，公差d=2。前n项和公式为：Sn=a1*n+[n*(n-1)*d]/2或Sn=[n*(a1+an)]/2。注意：以上n均属于正整数。
 *
 * 选择排序: 从数组中选择一个最小的数字，将这个值放到数组的第一个
 *
 * 算法复杂度： O(n^2)
 *
 * 第一次内循环比较N - 1次，然后是N-2次，N-3次，……，最后一次内循环比较1次。
 *  共比较的次数是 (N - 1) + (N - 2) + ... + 1，求等差数列和，得 (N - 1 + 1)* N / 2 = N^2 / 2。
 * 舍去最高项系数，其时间复杂度为 O(N^2)。
 *
 */
public class SelectionSortExample {


    public static void main(String[] args) {

        int number =1000000;
        int rangeL = 0;
        int rangeR = 1000000;

        Integer[] arrays = SortUtil.generateRandomArray(number,rangeL,rangeR);
        SortUtil.calculateTime("selection sort",SelectionSortExample::selectionSort,arrays);
       // SortUtil.printArray(arrays);



    }


    /**
     * 选择排序 从小到大  算法复杂度 O(n^2) 平方阶
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
            swap(comparableArray,i,minIndex);

        }
    }

   private static void swap(Object[] arr, int i, int j) {
        Object obj = arr[i];
        arr[i] = arr[j];
        arr[j] = obj;

    }




}
