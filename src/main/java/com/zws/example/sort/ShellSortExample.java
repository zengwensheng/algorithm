package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/17
 *
 * sheel 排序
 *
 *
 */
public class ShellSortExample {

    public static void main(String[] args) {
        int n = 100000;
        int rangeL = 0;
        int rangeR = 100000;
        int swapTimes = 10;

        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);

        /**
         * 最差测试
         */
        SortUtil.calculateTime("Worst: shell sort", ShellSortExample::shellSort, arrays);
        SortUtil.printArray(arrays);
    }


    /**
     *  sheel 排序  从小到大
     *
     * 代码                                     执行次数                        声明变量次数
     *
     * @param comparableArray
     */
    public static void shellSort(Comparable[] comparableArray) {
        Comparable indexComparable;
        for (int d = comparableArray.length / 2; d > 0; d /= 2) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < comparableArray.length; j += d) {
                    int k = j+d;
                    indexComparable = comparableArray[k];
                    for (; k-d >= 0 && indexComparable.compareTo(comparableArray[k - d]) == -1; k -= d) {
                        comparableArray[k] = comparableArray[k - d];
                    }
                    comparableArray[k] = indexComparable;

                }
            }
        }

    }


}
