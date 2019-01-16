package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/16
 *
 * 插入排序
 *    从数组第二个开始，跟数组左边的比较，找到合适的位置插入
 *
 *
 *
 */
public class InsertionSortExample {

    public static void main(String[] args) {

        int number = 100000;
        int rangeL = 0;
        int rangeR = 1000;
        int swapTimes = 10;



        Integer[] arrays = SortUtil.generateRandomArray(number, rangeL, rangeR);
        Integer[] arraysAdvance1 = arrays.clone();
        Integer[] arraysAdvance2 = arrays.clone();

        /**
         * 最差测试
         */
        SortUtil.calculateTime("Worst: insertion  sort", InsertionSortExample::insertionSort, arrays);
        //SortUtil.printArray(arrays);

        SortUtil.calculateTime("Worst: insertion advance1 sort", InsertionSortExample::insertionSortAdvance1, arraysAdvance1);
        //SortUtil.printArray(arrays);

        SortUtil.calculateTime("Worst: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, arraysAdvance2);
        //SortUtil.printArray(arrays);





        /**
         * 最优测试
         */
        arrays = SortUtil.generateNearlyOrderedArray(number, swapTimes);

        System.out.println("Optimal: sorted "+SortUtil.isSorted(arrays));
        SortUtil.calculateTime("Optimal: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, arraysAdvance2);
       // SortUtil.printArray(arrays);

    }

    /**
     * 插入排序：
     *
     *
     *
     *
     *
     * @param comparableArray
     */
    public static void insertionSort(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {

            for (int j = i;
                 j > 0 && comparableArray[j].compareTo(comparableArray[j - 1]) == -1;
                 j--) {
                SortUtil.swap(comparableArray, j, j - 1);
            }

        }
    }

    /**
     *
     * @param comparableArray
     */
    public static void insertionSortAdvance1(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {

            for (int j = i; j > 0; j--) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                    continue;
                }
                break;
            }

        }
    }


    /**
     *
     * 代码                                              执行次数
     *
     * 插入排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     * @param comparableArray
     */
    public static void insertionSortAdvance2(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            Comparable indexComparable = comparableArray[i];
            int j;
            for (j = i; j > 0; j--) {
                if (indexComparable.compareTo(comparableArray[j - 1]) == -1) {

                    comparableArray[j] = comparableArray[j - 1];
                    continue;
                }
                break;
            }
            comparableArray[j] = indexComparable;

        }
    }
}
