package com.zws.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/14
 *
 * 常见的算法复杂度：
 *    常数阶O(1),对数阶O(log2n)(以2为底n的对数，下同),线性阶O(n),
 *    线性对数阶O(nlog2n),平方阶O(n^2)，立方阶O(n^3)
 *
 */
public interface SortUtil {


    /**
     * 生成一组随机数组 左闭右开
     *
     * @param n      数组大小
     * @param rangeL 最小值
     * @param rangeR 最大值
     * @return
     */
    static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL <= rangeR);

        Integer[] integers = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            integers[i] = random.nextInt(rangeR - rangeL) + rangeL + 1;
        }

        return integers;

    }


    /**
     * 生成一组近乎有序的数组
     *   从小到大
     * @param n      数组大小
     * @param swapTimes 这个数越小数字 数组越有序
     * @return
     */
    static Integer[] generateNearlyOrderedArray(int n,int swapTimes) {

        Integer[] integers = new Integer[n];
        for (int i = 0; i < n; i++) {
                integers[i] = i;
        }

        Random random = new Random();
        for(int i =0;i<swapTimes;i++){
            swap(integers, random.nextInt(n),random.nextInt(n));
        }

        return integers;

    }


    /**
     * 生成一组近乎有序的数组
     *   从大到小
     * @param n      数组大小
     * @param swapTimes 这个数越小数字 数组越有序
     * @return
     */
    static Integer[] generateNearlyOrderedArrayFromBigToSmall(int n,int swapTimes) {

        Integer[] integers = new Integer[n];
        for (int i = n; i > 0; i--) {
            integers[i-1] = i;
        }

        Random random = new Random();
        for(int i =0;i<swapTimes;i++){
            swap(integers, random.nextInt(n),random.nextInt(n));
        }

        return integers;

    }




    /**
     * 打印数组元素
     *
     * @param arr
     */
    static void printArray(Object[] arr) {
        Arrays.stream(arr).forEach(o -> {
            System.out.print(o + "  ");
        });
        System.out.println();
    }

    /**
     * 判断数组排序是否正确
     *
     * @param comparableArray
     * @return
     */
    static boolean isSorted(Comparable[] comparableArray) {

        Comparable old = null;
        Integer comparableInteger = null;
        for (Comparable comparable : comparableArray) {
            if (old == null) {
                old = comparable;
                continue;
            }

            if (comparableInteger == null || comparableInteger == 0) {
                comparableInteger = comparable.compareTo(old);
            }

            if (comparable.compareTo(old) != comparableInteger && comparable.compareTo(old) != 0) {
                return false;
            }

            old = comparable;


        }


        return true;
    }



    /**
     * 数组中位置交换
     * @param arr
     * @param i
     * @param j
     */
    static void swap(Object[] arr, int i, int j) {
        Object obj = arr[i];
        arr[i] = arr[j];
        arr[j] = obj;

    }




}
