package com.zws.example.struct.stack;


/**
 *
 * 栈：
 *    线性结构，只能从一端添加元素，也只能从一端取出元素，这一端被称为栈顶
 *
 * 后进先出  Last In First Out (LIFO)
 *
 * 应用：
 *    Undo 操作（撤销）
 *    程序调用的系统栈
 *    括号匹配 - 编译器
 *
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 入栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈顶 第一个元素
     * @return
     */
    E peek();

}
