package com.zws.example.leetcode;

import com.zws.util.SortUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/13
 * <p>
 * <p>
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice
 * <p>
 * <p>
 * 给定一个整数数组，返回两个数字的索引，使它们相加到特定目标。
 * <p>
 * 您可以假设每个输入只有一个解决方案，并且您可能不会两次使用相同的元素。
 */
public class Solution_1 {


    public static void main(String[] args) {
        int n = 100000;
        int rangeL = 1;
        int rangeR = 2;
        int target = 6;


        Integer[] arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);
        arrays[1]=3;
        arrays[2]=3;

        SortUtil.printArray(Solution_1.calculateTime("Optimal: Two Sum", Solution_1::twoSum, arrays,target));
        SortUtil.printArray(Solution_1.calculateTime("Optimal: Two Sum Advance1", Solution_1::twoSumAdvance1, arrays,target));



        arrays = SortUtil.generateRandomArray(n, rangeL, rangeR);
        arrays[n-1]=3;
        arrays[n-2]=3;

        /**
         * 最差测试
         */
        SortUtil.printArray(Solution_1.calculateTime("Worst: Two Sum", Solution_1::twoSum, arrays,target));
        SortUtil.printArray(Solution_1.calculateTime("Optimal: Two Sum Advance1", Solution_1::twoSumAdvance1, arrays,target));




    }


    /**
     * 计算一个整数数组，返回两个数字的索引，使它们相加到特定目标。
     * 时间复杂度为  最优  O (1)
     *              最差 O（n^2）
     * @param nums
     * @param target
     * @return
     */
    static Integer[] twoSum(Integer[] nums, int target) {
        Integer[] result = new Integer[2];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                continue;
            }
            for (int j = 0; j < nums.length && j != i; j++) {
                if (target - nums[i] == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }


    /**
     * 计算一个整数数组，返回两个数字的索引，使它们相加到特定目标。
     * 时间复杂度为  最优  O (1)
     *              最差 O（n）
     * @param nums
     * @param target
     * @return
     */
    static Integer[] twoSumAdvance1(Integer[] nums, Integer target) {
        Integer [] result = new Integer[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                result[1] = i;
                result[0] = map.get(target-nums[i]);
                return result;
            }
            map.put(nums[i],i);
        }
        return result;
    }

    /**
     * 计算
     *
     * @param name  名字
     * @param biFunction  调用的方法
     * @param array    数组
     * @param target   目标值
     */
    static  Integer[] calculateTime(String name, BiFunction<Integer[],Integer,Integer[]> biFunction, Integer[] array, Integer target) {

        Instant startInstant = Instant.now();
        Integer[] result =  biFunction.apply(array,target);
        Instant endInstant = Instant.now();

        System.out.println(name + ": " + Duration.between(startInstant, endInstant).toMillis() + "ms");
        return result;
    }
}
