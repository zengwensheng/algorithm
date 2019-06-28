package com.zws.example.sort;

import com.zws.util.CalculateTimeUtil;
import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/6/26
 */
public class MergerSortExample {

    public static void main(String[] args) {
        int n = 10000;
        int rangeL = 0;
        int rangeR = 10000;
        int swapTimes = 0;

        Integer[] arrays = SortUtil.generateRandomArray(n,rangeL,rangeR);


        arrays = new Integer[]{10,8,9,7};

        CalculateTimeUtil.calculateTime("merger sort",MergerSortExample::mergerSort,arrays);
        SortUtil.printArray(arrays);

    }

    // 递归使用归并排序 将数组【l,r】区间的元素排序
    public static void mergerSort(Comparable[] comparableArray) {
        mergerSort(comparableArray,0,comparableArray.length-1);
    }


    // 递归使用归并排序 将数组【l,r】区间的元素排序
    private static void mergerSort(Comparable[] comparableArray, int l, int r) {


        if (l >= r) {
            return;
        }
        int mid = (r - l) / 2 + l;
        mergerSort(comparableArray, l, mid);
        mergerSort(comparableArray, mid + 1, l);
        merger(comparableArray, l, mid, r);

    }

    // 归并comparableArray数组中 【l,mid】区间 和【mid+1,r】区间的数据

    private static void merger(Comparable[] comparableArray, int l, int mid, int r) {

        Comparable[] aux = new Comparable[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = comparableArray[i];
        }


        for (int i = 0, j = 0;i>=mid-l&&j>=r-mid-1; ) {

            if (i >= mid - l) {
                comparableArray[i + j + l] = aux[j + mid + 1];
                j++;
            } else if (j >= r - mid-1) {
                comparableArray[i + j + l] = aux[i];
                i++;
            } else if (aux[j + mid + 1].compareTo(aux[i]) < 0) {
                comparableArray[i + j + l] = aux[j + mid + 1];
                j++;

            } else {
                comparableArray[i + j + l] = aux[i];
                i++;
            }
        }

    }

}
