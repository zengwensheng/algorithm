package com.zws.example.sort;

import com.zws.util.CalculateTimeUtil;
import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/16
 *
 * 插入排序
 *   每步将一个待排序的数组，按其关键码值的大小插入前面已经排序的数组中适当位置上，直到全部插入完为止。
 * 稳定性： 稳定，不会打乱数组中两个相等元素的顺序
 *
 */
public class InsertionSortExample {

    public static void main(String[] args) {

        int n = 10000;
        int rangeL = 0;
        int rangeR = 10000;
        int swapTimes = 0;


        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);
        Integer[] arraysAdvance1 = arrays.clone();
        Integer[] arraysAdvance2 = arrays.clone();

        /**
         * 最差测试
         */
        CalculateTimeUtil.calculateTime("Worst: insertion  sort", InsertionSortExample::insertionSort, arrays);
        //SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Worst: insertion advance1 sort", InsertionSortExample::insertionSortAdvance1, arraysAdvance1);
        //SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Worst: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, arraysAdvance2);
        //SortUtil.printArray(arrays);


        /**
         * 最优测试
         */
        arrays = SortUtil.generateNearlyOrderedArray(n, swapTimes);
        arraysAdvance1 = arrays.clone();
        arraysAdvance2 = arrays.clone();

        CalculateTimeUtil.calculateTime("Optimal: insertion  sort", InsertionSortExample::insertionSort, arrays);
        //SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Optimal: insertion advance1 sort", InsertionSortExample::insertionSortAdvance1, arraysAdvance1);
        //SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Optimal: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, arraysAdvance2);
        //SortUtil.printArray(arrays);

    }

    /**
     * 插入排序 从小到大
     *
     *
     * 代码                                                  执行次数                         声明变量次数
     * compareTo                                              (n^2-n)/2
     * swap                                               最差：1.5n^2-1.5n  最优：0           最差：(n^2-n)/2  最优：0
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  0.5n^2-0.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)
     * 最差： T(n) = 2n^2+2n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) = 0.5n^2-0.5n   得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     * @param comparableArray
     */
    public static void insertionSort(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                }
            }
        }
    }

    /**
     * 插入排序 从小到大
     *
     * 代码                                                  执行次数                       声明变量次数
     * compareTo                                              n
     * swap                                               最差：1.5n^2-1.5n 最优：0          最优：(n^2-n)/2  最差：0
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n)
     * 最差： T(n) = 1.5n^2-2.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) = 0.5n^2-0.5n   得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     * @param comparableArray
     */
    public static void insertionSortAdvance1(Comparable[] comparableArray) {
        for (int i = 1; i < comparableArray.length; i++) {
            for (int j = i; j > 0 && comparableArray[j].compareTo(comparableArray[j - 1]) == -1; j--) {
                SortUtil.swap(comparableArray, j, j - 1);
            }

        }
    }


    /**
     * 插入排序 从小到大
     *
     * 通过数组右移的方式，来减少数组交换的次数
     *
     *
     * 代码                                                  执行次数                     声明变量次数
     * indexComparable  = comparableArray[i];                  n
     *  int j;                                                 n
     * compareTo                                       最好：n   最坏：(n^2-n)/2+n
     * comparableArray[j] = comparableArray[j - 1]    最好：0   最坏：(n^2-n)/2
     * comparableArray[j] = indexComparable;                  n
     *
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  4n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：） O(n)
     * 最差： T(n) = n^2+3n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     * 平均 O(n^2)
     *
     * 由此得出上面测试的结论：
     * 在数组近乎是顺序的情况下 此时的时间复杂度为O(n)
     * 反之时间复杂度为O(n^2)
     *
     * 缺点：如果两个相邻的元素，在未排序的数组中相隔太远，该排序算法产生的赋值次数会增加，不适合大数组
     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *    T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *
     *
     *
     * @param comparableArray
     */
    public static void insertionSortAdvance2(Comparable[] comparableArray) {
        Comparable indexComparable;
        for (int i = 1; i < comparableArray.length; i++) {
            indexComparable  = comparableArray[i];
            int j;
            for (j = i; j > 0 && indexComparable.compareTo(comparableArray[j - 1]) == -1; j--) {
                comparableArray[j] = comparableArray[j - 1];
            }
            comparableArray[j] = indexComparable;

        }
    }
}
