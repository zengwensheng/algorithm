package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/17
 */
public class ShellSortExample {

    public static void main(String[] args) {
        int n = 10;
        int rangeL = 0;
        int rangeR = 10;
        int swapTimes = 10;

        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);

        /**
         * 最差测试
         */
        SortUtil.printArray(arrays);
        SortUtil.calculateTime("Worst: shell sort", ShellSortExample::shellSort, arrays);
        SortUtil.printArray(arrays);
    }


    public static void shellSort(Comparable[] comparableArray) {
        Comparable indexComparable;
        for (int d = comparableArray.length / 2; d > 0; d /= 2) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < comparableArray.length; j += d) {
                    indexComparable = comparableArray[j];
                    int k;
                    for (k = j; k - d >= 0 && indexComparable.compareTo(comparableArray[k - d]) == -1; k -= d) {
                        comparableArray[k] = comparableArray[k - d];
                    }
                    comparableArray[k] = indexComparable;

                }
            }
        }

    }


}
