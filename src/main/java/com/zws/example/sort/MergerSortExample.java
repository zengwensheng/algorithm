package com.zws.example.sort;

import com.zws.util.CalculateTimeUtil;
import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/6/26
 * <p>
 * 归并排序（MERGE-SORT）
 * O（nlogn）
 * 是建立在归并操作上的一种有效的排序算法,该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 */
public class MergerSortExample {

    public static void main(String[] args) {
        int n = 100000;
        int rangeL = 0;
        int rangeR = 100000000;
        int swapTimes = 100;

        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);
        Integer[] arrays2 = arrays.clone();
        Integer[] arrays3 = arrays.clone();

        CalculateTimeUtil.calculateTime("Worst: merger sort", MergerSortExample::mergerSort, arrays);
        //SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Worst: merger sort 2", MergerSortExample::mergerSort2, arrays2);
        // SortUtil.printArray(arrays2);

        CalculateTimeUtil.calculateTime("Worst: merger sort bu", MergerSortExample::mergeSortBU, arrays3);
        //SortUtil.printArray(arrays3);

        arrays = SortUtil.generateNearlyOrderedArray(n, swapTimes);
        arrays2 = arrays.clone();
        arrays3 = arrays.clone();

        CalculateTimeUtil.calculateTime("Optimal: merger sort", MergerSortExample::mergerSort, arrays);
        // SortUtil.printArray(arrays);

        CalculateTimeUtil.calculateTime("Optimal: merger sort 2", MergerSortExample::mergerSort2, arrays2);
        // SortUtil.printArray(arrays2);

        CalculateTimeUtil.calculateTime("Optimal: merger sort bu", MergerSortExample::mergeSortBU, arrays3);
        // SortUtil.printArray(arrays3);


    }

    // 递归使用归并排序 将数组【l,r】区间的元素排序
    public static void mergerSort(Comparable[] comparableArray) {
        mergerSort(comparableArray, 0, comparableArray.length - 1);
    }


    // 递归使用归并排序 将数组【l,r】区间的元素排序
    private static void mergerSort(Comparable[] comparableArray, int l, int r) {


        if (l >= r) {
            return;
        }
        int mid = (r - l) / 2 + l;
        mergerSort(comparableArray, l, mid);
        mergerSort(comparableArray, mid + 1, r);
        merger(comparableArray, l, mid, r);

    }


    // 递归使用归并排序 将数组【l,r】区间的元素排序
    public static void mergerSort2(Comparable[] comparableArray) {
        mergerSort2(comparableArray, 0, comparableArray.length - 1);
    }


    // 递归使用归并排序 将数组【l,r】区间的元素排序
    private static void mergerSort2(Comparable[] comparableArray, int l, int r) {

        // 对于小规模的数组 进行查询排序
        if (r - l <= 15) {
            InsertionSortExample.insertionSort(comparableArray, l, r);
            return;
        }
        int mid = (r - l) / 2 + l;
        mergerSort(comparableArray, l, mid);
        mergerSort(comparableArray, mid + 1, r);
        //对于数组[mid] <= 数组[mid+1] 的情况，不进行merge
        //对于近乎有序的数组非常有效，但是对于一般情况，有一定的性能损失
        if (comparableArray[mid].compareTo(comparableArray[mid + 1]) > 0) {
            merger(comparableArray, l, mid, r);
        }

    }


    // 自底向上的归并排序
    public static void mergeSortBU(Comparable[] comparableArray) {
        int n = comparableArray.length;
        int segmentSize = 15;
        for (int i = 0; i < n; i += segmentSize) {
            InsertionSortExample.insertionSort(comparableArray, i, Math.min(i + segmentSize - 1, n - 1));
        }


        for (int sz = segmentSize; sz <= n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                if (comparableArray[i + sz - 1].compareTo(comparableArray[i + sz]) > 0)
                    merger(comparableArray, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }


    }

    // 归并comparableArray数组中 【l,mid】区间 和【mid+1,r】区间的数据

    private static void merger(Comparable[] comparableArray, int l, int mid, int r) {

        Comparable[] aux = new Comparable[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = comparableArray[i];
        }

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {

            if (i > mid) {
                comparableArray[k] = aux[j - l];
                j++;
            } else if (j > r) {
                comparableArray[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) > 0) {
                comparableArray[k] = aux[j - l];
                j++;

            } else {
                comparableArray[k] = aux[i - l];
                i++;
            }
        }

    }

}
