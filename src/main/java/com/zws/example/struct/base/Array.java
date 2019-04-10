package com.zws.example.struct.base;

import java.util.*;
import java.util.function.Consumer;

/**
 * 动态数组：
 *    线性数据结构,底层依托静态数组，通过扩容（resize）解决固定容量问题
 *
 * 什么是数组
 *     同类数据元素的集合，在计算机中以连续的地址存储，编译时确定长度，无法改变。
 *
 * 什么是动态数组
 *     数据结构中顺序表的物理实现，同类数据元素的集合，在计算机中以连续的地址存储，大小在创建时决定，但是可以改变。
 *
 * 为什么要使用动态数组
 *       支持随机访问,查询速度快。但是插入和删除都需要移动元素，比起链表开销较大
 *
 * 数组最好应用于"索引有语意"的情况
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/27
 */
public class Array<E> implements  Iterable<E> {

    private E[] data;

    private int size;

    private int modCount;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
        modCount=0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr){
        data = arr;
        size = arr.length;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 时间复杂度： O(n/2) = O(n)
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        modCount ++;

        if (size == data.length) {
            resize(size * 2);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    /**
     * 时间复杂度：O(1)
     */

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 时间复杂度:O(n)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    private void resize(int newCapacity) {
        data = Arrays.copyOf(data, newCapacity);
    }

    /**
     * 时间复杂度:O(1)
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    /**
     * 时间复杂度:O(1)
     */
    public E getFirst(){
        return data[0];
    }
    /**
     * 时间复杂度:O(1)
     */
    public E getLast()
    {
        return data[size-1];
    }

    /**
     * 时间复杂度:O(1)
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }


    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 时间复杂度：O(n)
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 时间复杂度：O(n/2) = O(n)
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        modCount ++;
        E ret = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; // loitering objects != memory leak


        /**
         * 防止复杂度震荡
         */
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 时间复杂度：O(n)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 时间复杂度：O(1)
     */
    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public void swap(int i,int j){
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements  Iterator<E>{

        int cursor = 0;

        int lastRet = -1;

        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public E next() {
            checkForComodification();
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(lastRet<0){
                throw  new IllegalArgumentException();
            }
            checkForComodification();
            try {
                Array.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();

    }

    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++)
            arr.addLast(i);

        for (Integer i:arr){
            System.out.println(i);
        }

        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for (int i = 0; i < 4; i++) {
            arr.removeFirst();
            System.out.println(arr);
        }
    }

}
