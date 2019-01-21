package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/17
 * <p>
 * shell 排序
 *    希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * 不稳定
 */
public class ShellSortExample {

    public static void main(String[] args) {
        int n = 10;
        int rangeL = 0;
        int rangeR = 10;
        int swapTimes = 10;

        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);

        SortUtil.printArray(arrays);
        SortUtil.calculateTime("Worst: shell sort", ShellSortExample::shellSort, arrays);
        //  SortUtil.printArray(arrays);

    }


    /**
     * shell 排序  从小到大
     *
     * 假如这个数组10个元素   n=10
     *  当增量等于2时 那么d=n/2=5
     *        插入排序比较次数:   (1)*d =5
     *
     *  当增量等于4时 那么d=n/4=2
     *        插入排序比较次数（等差数列求和)等于: (1+2+3+4+5)*d=15*2 = 30
     *
     *  当增量等于8时 那么d=n/8=1
     *        插入排序比较次数（等差数列求和)等于: (1+2+3+4+5+6+7+8+9+10)*d=55*1 = 55
     *  得出
     *    (1+n/d)*(n/d)/2*d
     *
     * @Todo 计算时间复杂度
     *
     *
     *  时间复杂度： O(n^(1.3——2))
     *
     * @param comparableArray
     */
    public static void shellSort(Comparable[] comparableArray) {
        Comparable indexComparable;
        for (int d = comparableArray.length / 2; d > 0; d /= 2) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < comparableArray.length; j += d) {
                    int k = j + d;
                    indexComparable = comparableArray[k];
                    for (; k - d >= 0 && indexComparable.compareTo(comparableArray[k - d]) == -1; k -= d) {
                        comparableArray[k] = comparableArray[k - d];
                    }
                    comparableArray[k] = indexComparable;

                }
            }
        }

    }




}
