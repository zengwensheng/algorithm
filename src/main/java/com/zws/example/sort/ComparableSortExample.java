package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/18
 */
public class ComparableSortExample {

    public static void main(String[] args) {

        int n = 10000;
        int rangeL = 0;
        int rangeR = 10000;
        int swapTimes = 10;

        Integer [] selectionArray = SortUtil.generateRandomArray(n,rangeL,rangeR);
        Integer [] insertionArray = selectionArray.clone();
        Integer [] bubbleArray = selectionArray.clone();

        SortUtil.calculateTime("Worst: selection sort",SelectionSortExample::selectionSort,selectionArray);
        SortUtil.calculateTime("Worst: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, insertionArray);
        SortUtil.calculateTime("Worst: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,bubbleArray);


        selectionArray = SortUtil.generateNearlyOrderedArray(n, swapTimes);
        insertionArray = selectionArray.clone();
        bubbleArray = selectionArray.clone();

        SortUtil.calculateTime("Optimal: selection sort",SelectionSortExample::selectionSort,selectionArray);
        SortUtil.calculateTime("Optimal: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, insertionArray);
        SortUtil.calculateTime("Optimal: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,bubbleArray);
    }
}
