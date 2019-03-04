package com.zws.example.struct.stack;

import com.zws.example.struct.array.Array;

/**
 *
 *  用数组实现栈
 *
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/27
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> data;

    public ArrayStack(Integer newCapacity){
        data = new Array<>(newCapacity);
    }

    public ArrayStack(){
        data = new Array<>();
    }

    public int getCapacity(){
        return data.getCapacity();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }


    /**
     * 时间复杂度： 均摊O(1)
     */
    @Override
    public void push(E e) {
         data.addLast(e);
    }

    /**
     * 时间复杂度： 均摊O（1）
     */
    @Override
    public E pop() {
        return data.removeLast();
    }

    /**
     * 时间复杂度： O（1）
     */
    @Override
    public E peek() {
        return data.getLast();
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
