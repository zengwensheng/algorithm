package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/17
 *
 * 冒泡排序：
 *
 */
public class BubbleSortExample {

    public static void main(String[] args) {

        int number =100000;
        int rangeL = 0;
        int rangeR = 10000;
        int swapTimes = 0;

        Integer[] arrays = SortUtil.generateRandomArray(number,rangeL,rangeR);
        Integer[] arrays1 = arrays.clone();

        /**
         * 最差测试
         */
      /*  SortUtil.calculateTime("Worst: bubble sort",BubbleSortExample::bubbleSort,arrays);
        SortUtil.printArray(arrays);
        SortUtil.calculateTime("Worst: bubble advance1 sort",BubbleSortExample::bubbleSortAdvance1,arrays1);
        SortUtil.printArray(arrays);*/


        arrays = SortUtil.generateNearlyOrderedArray(number,swapTimes);
        arrays1 = arrays.clone();

        /**
         * 最优测试
         */
       /* SortUtil.calculateTime("Optimal: bubble sort",BubbleSortExample::bubbleSort,arrays);
        SortUtil.printArray(arrays);*/
        SortUtil.calculateTime("Optimal: bubble advance1 sort",BubbleSortExample::bubbleSortAdvance1,arrays1);
        SortUtil.printArray(arrays1);


    }


    /**
     * 冒泡排序 从小到大
     *
     *
     *
     *
     * @param comparableArray
     */
    public static void bubbleSort(Comparable[] comparableArray) {
        for (int i = 0; i < comparableArray.length; i++) {
            for (int j = 1; j < comparableArray.length; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                }
            }
        }

    }

    /**
     * 冒泡排序 从小到大
     *
     *
     *
     *
     * @param comparableArray
     */
    public static void bubbleSortAdvance1(Comparable[] comparableArray) {
        Boolean flag;

        for (int i = 0; i < comparableArray.length; i++) {
            flag = false;
            for (int j = 1; j < comparableArray.length; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
        }

    }


    /**
     * 冒泡排序 从小到大
     *
     *
     *
     *
     * @param comparableArray
     */
    public static void bubbleSortAdvance2(Comparable[] comparableArray) {
        Boolean flag;
        int j;
        int maxIndex = comparableArray.length;

        for (int i = 0; i < comparableArray.length; i++) {
            flag = false;
            for (j = 1; j < maxIndex; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
            maxIndex = j;
        }

    }
}
