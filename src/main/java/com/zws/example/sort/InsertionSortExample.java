package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/16
 * <p>
 * 插入排序
 * 从数组第二个开始，跟数组左边的比较，找到合适的位置插入
 */
public class InsertionSortExample {

    public static void main(String[] args) {

        int number = 100000;
        int rangeL = 0;
        int rangeR = 1000;
        int swapTimes = 0;


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
        arraysAdvance1 = arrays.clone();
        arraysAdvance2 = arrays.clone();

        SortUtil.calculateTime("Optimal: insertion  sort", InsertionSortExample::insertionSort, arrays);
        //SortUtil.printArray(arrays);

        SortUtil.calculateTime("Optimal: insertion advance1 sort", InsertionSortExample::insertionSortAdvance1, arraysAdvance1);
        //SortUtil.printArray(arrays);

        SortUtil.calculateTime("Optimal: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, arraysAdvance2);
        //SortUtil.printArray(arrays);

    }

    /**
     * 插入排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     *
     * 代码                                                  执行次数
     * compareTo                                              (n^2-n)/2
     * swap                                               最优：1.5n^2-1.5n  最差：0
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  0.5n^2-0.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)
     * 最差： T(n) = 2n^2+2n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * @param comparableArray
     */
    private static void insertionSort(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                }
            }
        }
    }

    /**
     * 插入排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     *
     * 代码                                                  执行次数
     * compareTo                                              n
     * swap                                               最优：1.5n^2-1.5n  最差：0
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)
     * 最差： T(n) = 1.5n^2-2.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * @param comparableArray
     */
    private static void insertionSortAdvance1(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            for (int j = i; j > 0 && comparableArray[j].compareTo(comparableArray[j - 1]) == -1; j--) {
                SortUtil.swap(comparableArray, j, j - 1);
            }

        }
    }


    /**
     * 插入排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     * 通过数组右移的方式，来减少数组交换的次数
     *
     *
     * 代码                                                  执行次数
     * Comparable indexComparable                              n
     * compareTo                                       最好：n   最坏：(n^2-n)/2+n
     * comparableArray[j] = comparableArray[j - 1]    最好：0   最坏：(n^2-n)/2
     * comparableArray[j] = indexComparable;                  n
     *
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  3n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：） O(n)
     * 最差： T(n) = n^2+2n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * 由此得出上面测试的结论：
     * 在数组近乎是顺序的情况下 此时的时间复杂度为O(n)
     * 反之时间复杂度为O(n^2)
     *
     * @param comparableArray
     */
    private static void insertionSortAdvance2(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            Comparable indexComparable = comparableArray[i];
            int j;
            for (j = i; j > 0 && indexComparable.compareTo(comparableArray[j - 1]) == -1; j--) {
                comparableArray[j] = comparableArray[j - 1];
            }
            comparableArray[j] = indexComparable;

        }
    }
}
