package com.zws.example.struct.queue;

import com.zws.example.struct.base.Array;

/**
 *
 * 数组队列:
 *   底层依托动态数组 {@link Array}
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/4
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> data;


    public ArrayQueue() {
        data = new Array<>();
    }

    public ArrayQueue(int capacity) {
        data = new Array<>(capacity);
    }


    @Override
    public int getSize() {
        return data.getSize();
    }

    public int getCapacity() {
        return data.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 时间复杂度： 均摊 O(1)
     */
    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    /**
     * 时间复杂度： O(n)
     */
    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    /**
     * 时间复杂度： O(1)
     */
    @Override
    public E getFront() {
        return data.getFirst();
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
