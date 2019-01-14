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

    static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL <= rangeR);

        Integer[] integers = new Integer[n];
        Random random = new Random();
        for (int i=0;i<n;i++){
            integers[i] = random.nextInt(rangeR-rangeL+1)+rangeL;
        }

        return integers;

    }



    static void printArray(Object[] arr) {
        Arrays.stream(arr).forEach(System.out::print);
    }

    static boolean isSorted(Comparable[] comparableArray) {

        Comparable old = null;
        Integer comparableInteger = null;
        for (Comparable comparable : comparableArray) {
            if (old == null) {
                old = comparable;
                continue;
            }

            if (comparableInteger == null) {
                comparableInteger = comparable.compareTo(old);
            }

            if (comparable.compareTo(old) != comparableInteger) {
                return false;
            }

            old = comparable;


        }


        return true;
    }


    static void calculateTime(String sortName, Consumer<Comparable<Object>[]> consumer, Comparable<Object>[] comparableArray) {

        Instant startInstant = Instant.now();
        consumer.accept(comparableArray);
        Instant endInstant = Instant.now();
        if (!isSorted(comparableArray)) {
            System.out.println("排序不正确");
            return;
        }
        System.out.println(sortName + ": " + Duration.between(startInstant, endInstant).toMillis() + "ms");
    }


}
