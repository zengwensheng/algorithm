package com.zws.example.sort;

import com.zws.util.CalculateTimeUtil;
import com.zws.util.SortUtil;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/17
 *
 * 冒泡排序：
 *     比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *     对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 *     针对所有的元素重复以上的步骤，除了最后一个。
 *     持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * 稳定性： 稳定，不会打乱数组中两个相等元素的顺序
 *
 *
 *
 */
public class BubbleSortExample {

    public static void main(String[] args) {

        int n =3000;
        int rangeL = 0;
        int rangeR = 1000;
        int swapTimes = 10;

        Integer[] arrays = SortUtil.generateRandomArray(n,rangeL,rangeR);
        Integer[] arrays1 = arrays.clone();
        Integer[] arrays2 = arrays.clone();

        /**
         * 最差测试
         */
        CalculateTimeUtil.calculateTime("Worst: bubble sort",BubbleSortExample::bubbleSort,arrays);
        SortUtil.printArray(arrays);
        CalculateTimeUtil.calculateTime("Worst: bubble advance1 sort",BubbleSortExample::bubbleSortAdvance1,arrays1);
        SortUtil.printArray(arrays1);
        CalculateTimeUtil.calculateTime("Worst: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,arrays2);
        SortUtil.printArray(arrays2);

        arrays = SortUtil.generateNearlyOrderedArray(n,swapTimes);
        arrays1 = arrays.clone();
        arrays2 = arrays.clone();


        /**
         * 最优测试
         */
        CalculateTimeUtil.calculateTime("Optimal: bubble sort",BubbleSortExample::bubbleSort,arrays);
        SortUtil.printArray(arrays);
        CalculateTimeUtil.calculateTime("Optimal: bubble advance1 sort",BubbleSortExample::bubbleSortAdvance1,arrays1);
        SortUtil.printArray(arrays1);
        CalculateTimeUtil.calculateTime("Optimal: bubble advance2 sort",BubbleSortExample::bubbleSortAdvance2,arrays2);
        SortUtil.printArray(arrays2);


    }


    /**
     * 冒泡排序 从小到大
     *
     * 代码                                                  执行次数                         声明变量次数
     * compareTo                                             n^2
     * swap                                                最好：0 最差 n^2                   最好：0 最差 n^2
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) =  n^2  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)
     * 最差： T(n) = 2n^2  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) = n^2   得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     *
     * @param comparableArray
     */
    public static void bubbleSort(Comparable[] comparableArray) {
        for (int i = 0; i < comparableArray.length; i++) {
            for (int j = 1; j < comparableArray.length; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                }
            }
        }

    }

    /**
     * 冒泡排序 从小到大
     *
     * 代码                                     执行次数                        声明变量次数
     * flag = false;                              n
     * compareTo                              最优：n  最差：n^2
     * swap                                   最优：0  最差：n^2                最优：0  最差：n^2
     * flag=true;                             最优：0  最差：n^2
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) = 2n   得出时间复杂度为（找到最高次项，去掉最高次项系数得出：） O(n)
     * 最差： T(n) = 2n^2+n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)

     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) = 2n^2   得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     * @param comparableArray
     */
    public static void bubbleSortAdvance1(Comparable[] comparableArray) {
        Boolean flag;

        for (int i = 0; i < comparableArray.length; i++) {
            flag = false;
            for (int j = 1; j < comparableArray.length; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
        }

    }


    /**
     * 冒泡排序 从小到大
     *
     * 代码                                     执行次数                        声明变量次数
     * flag = false;                              n
     * compareTo                              最优：n 最差：(n^2-n)/2
     * swap                                   最优：0 最差：(n^2-n)/2           最优：0 最差：(n^2-n)/2
     * flag=true;                             最优：0  最差：n^2
     * maxIndex--;                            最优：0  最差：n
     *
     * 求时间复杂度：
     * 根据以上得出时间频度
     * 最优： T(n) = 2n   得出时间复杂度为（找到最高次项，去掉最高次项系数得出：） O(n)
     * 最差： T(n) = n^2+n  得出时间复杂度为（找到最高次项，去掉最高次项系数得出：）O(n^2)
     * 平均 O(n^2)
     *
     * 根据冒泡排序的特征来看
     *   只有在数组近乎相似，并且两个元素未排序的数组中不能隔太远和两个元素在已排序的数组中不能隔太运 才是 O(n) 复杂度
     *   不然是O(n^2)
     *
     *
     * 求空间复杂度：
     *  根据以上（声明变量次数）得出所消耗的空间：
     *   最优： T(n) =  0  得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(0)
     *   最差： T(n) = 0.5n^2-0.5n   得出空间复杂度为（找到最高次项，去掉最高次项系数得出：）  O(n^2)
     *
     *
     *
     * @param comparableArray
     */
    public static void bubbleSortAdvance2(Comparable[] comparableArray) {
        Boolean flag;
        int j;
        int maxIndex = comparableArray.length;

        for (int i = 0; i < comparableArray.length; i++) {
            flag = false;
            for (j = 1; j < maxIndex; j++) {
                if (comparableArray[j].compareTo(comparableArray[j - 1]) == -1) {
                    SortUtil.swap(comparableArray, j, j - 1);
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
            maxIndex--;
        }

    }
}
