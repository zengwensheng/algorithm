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
     * 计算
     *
     * @param sortName
     * @param consumer
     * @param comparableArray
     */
    static void calculateTime(String sortName, Consumer<Comparable[]> consumer, Comparable[] comparableArray) {

        Instant startInstant = Instant.now();
        consumer.accept(comparableArray);
        Instant endInstant = Instant.now();
     //   if (!isSorted(comparableArray)) {
       //     System.out.println("排序不正确");
         //   return;
       // }
        System.out.println(sortName + ": " + Duration.between(startInstant, endInstant).toMillis() + "ms");
    }




}
