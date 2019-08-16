package com.zws.example.sort;

import com.zws.util.CalculateTimeUtil;
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
        int rangeR = 100000;
        int swapTimes = 10;

        Integer [] selectionArray = SortUtil.generateRandomArray(n,rangeL,rangeR);
        Integer [] insertionArray = selectionArray.clone();
        Integer [] bubbleArray = selectionArray.clone();
        Integer [] shellArray = selectionArray.clone();
        Integer [] mergeArray = selectionArray.clone();

        CalculateTimeUtil.calculateTime("Worst: selection sort",SelectionSortExample::selectionSort,selectionArray);
        CalculateTimeUtil.calculateTime("Worst: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, insertionArray);
        CalculateTimeUtil.calculateTime("Worst: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,bubbleArray);
        CalculateTimeUtil.calculateTime("Worst: shell sort", ShellSortExample::shellSort, shellArray);
        CalculateTimeUtil.calculateTime("Worst: merge sort", MergerSortExample::mergeSortBU, mergeArray);


        selectionArray = SortUtil.generateNearlyOrderedArray(n, swapTimes);
        insertionArray = selectionArray.clone();
        bubbleArray = selectionArray.clone();
        shellArray = selectionArray.clone();
        mergeArray = selectionArray.clone();

        CalculateTimeUtil.calculateTime("Optimal: selection sort",SelectionSortExample::selectionSort,selectionArray);
        CalculateTimeUtil.calculateTime("Optimal: insertion advance2 sort", InsertionSortExample::insertionSortAdvance2, insertionArray);
        CalculateTimeUtil.calculateTime("Optimal: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,bubbleArray);
        CalculateTimeUtil.calculateTime("Optimal: shell sort",ShellSortExample::shellSort,shellArray);
        CalculateTimeUtil.calculateTime("Optimal: merge sort", MergerSortExample::mergeSortBU, mergeArray);



    }
}
