package com.zws.util;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 */
public interface CalculateTimeUtil {

    /**
     * 计算排序时间
     *
     * @param sortName
     * @param consumer
     * @param comparableArray
     */
    static void calculateTime(String sortName, Consumer<Comparable[]> consumer, Comparable[] comparableArray) {

        Instant startInstant = Instant.now();
        consumer.accept(comparableArray);
        Instant endInstant = Instant.now();
        if (!SortUtil.isSorted(comparableArray)) {
            System.out.println("排序不正确");
            return;
        }
        System.out.println(sortName + ": " + Duration.between(startInstant, endInstant).toMillis() + "ms");
    }


    static <T> Long  calculateTime(Consumer<T>  objectConsumer, T object) {
        Instant start = Instant.now();
        objectConsumer.accept(object);
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }
}
