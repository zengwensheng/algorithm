package com.zws.example.struct.stack;

import com.zws.example.struct.array.Array;

/**
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

    @Override
    public void push(E e) {
         data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.get(0);
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
