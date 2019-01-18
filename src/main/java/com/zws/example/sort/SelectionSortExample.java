package com.zws.example.sort;

import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/14
 *
 *
 *
 * 选择排序:
 *      每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，
 *      然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推
 *
 * 稳定性： 不稳定，会打乱数组中两个相等元素的顺序
 *  选择排序是给每个位置选择当前元素最小的，比如给第一个位置选择最小的，
 *  在剩余元素里面给第二个元素选择第二小的，依次类推，直到第n-1个元素，第n个元素不用选择了，因为只剩下它一个最大的元素了。
 *  那么，在一趟选择，如果一个元素比当前元素小，而该小的元素又出现在一个和当前元素相等的元素后面，那么交换后稳定性就被破坏了。
 *  比较拗口，举个例子，序列5 8 5 2 9，我们知道第一遍选择第1个元素5会和2交换，那么原序列中两个5的相对前后顺序就被破坏了，
 *  所以选择排序是一个不稳定的排序算法。
 *
 */
public class SelectionSortExample {


    public static void main(String[] args) {

        int n =10000;
        int rangeL = 0;
        int rangeR = 1000;
        int swapTimes = 0;

        Integer[] arrays = SortUtil.generateRandomArray(n,rangeL,rangeR);

        /**
         * 最差测试
         */

        SortUtil.calculateTime("Worst: selection sort",SelectionSortExample::selectionSort,arrays);
        SortUtil.printArray(arrays);

        /**
         * 最优测试
         */
        arrays = SortUtil.generateNearlyOrderedArray(n, swapTimes);

        SortUtil.calculateTime("Optimal: selection sort",SelectionSortExample::selectionSort,arrays);
        SortUtil.printArray(arrays);



    }


    /**
     * 选择排序 从小到大  算法复杂度 O(n^2) 平方阶
     *
     *
     * 等差数列：
     *   是指从第二项起，每一项与它的前一项的差等于同一个常数的一种数列，常用A、P表示。这个常数叫做等差数列的公差，公差常用字母d表示。
     *   例如：1,3,5,7,9……2n-1。
     *   通项公式为：an=a1+(n-1)*d。首项a1=1，公差d=2。
     *   前n项和公式为：Sn=a1*n+[n*(n-1)*d]/2或Sn=[n*(a1+an)]/2。注意：以上n均属于正整数。
     *
     *
     * 第一次：n-1 第二次  n-2 .... 最后一次 1
     * 等差数列求和得： (1+n-1)*(n-1)/2
     *
     * 代码                                              执行次数                         声明变量次数
     *  minIndex = i;                                      n
     *  compareTo                                       (n^2-n)/2
     *  minIndex = j;                             最好：0  最差； (n^2-n)/2
     *  SortUtil.swap(....                            最好：0 最差；3n-3                 最好：0 最差；n-1
     *
     * 求时间复杂度：
     *  根据以上（执行次数）得出时间频度：
     *   最优： T(n) =  0.5n^2+0.5n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *   最差： T(n) =  n^2+2n-3 得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *  所以得出时间无论什么情况下复杂度为 O(n^2)
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) =  n-1 得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n)
     *
     * @param comparableArray
     */
    public static void selectionSort(Comparable[] comparableArray) {
        int minIndex;
        for (int i = 0; i < comparableArray.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < comparableArray.length ; j++) {
                if(comparableArray[minIndex].compareTo(comparableArray[j]) == 1) {
                    minIndex = j;
                }
            }

            if(i==minIndex){
                continue;
            }
            SortUtil.swap(comparableArray,i,minIndex);

        }
    }






}
