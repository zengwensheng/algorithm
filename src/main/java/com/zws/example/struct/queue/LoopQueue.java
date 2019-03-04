package com.zws.example.struct.queue;


import com.zws.example.struct.array.Array;

/**
 * 循环队列：
 * 为充分利用向量空间，克服"假溢出"现象的方法是：将向量空间想象为一个首尾相接的圆环，并称这种向量为循环向量。存储在其中的队列称为循环队列
 * <p>
 * 条件判断：
 * 队列是否为空：
 * 一  设置布尔值
 * 队列为空判断： front==tail & 布尔值为false
 * 队列为满判断： front==tail & 布尔值为true
 * 二 浪费一个空间
 * 队列为空判断： front==tail
 * 队列为满判断： (tail+1)%n == front
 * <p>
 * 出队：front=(front+1)%n
 * 入队：tail = (tail+1)%n
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/4
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front, tail;


    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = tail = 0;
    }


    @Override
    public int getSize() {
        if (tail > front) {
            return tail - front;
        }
        return tail + data.length - front;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }


    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;

    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity+1];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;

    }

    @Override
    public E dequeue() {
        E res = data[front];
        front = (front+1)%data.length;
        if(getCapacity()/4==getSize()&&getCapacity()!=0){
            resize(getCapacity()/2);
        }
        return res;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", data, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
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
