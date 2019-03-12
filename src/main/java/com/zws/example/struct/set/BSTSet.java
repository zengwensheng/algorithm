package com.zws.example.struct.set;

/**
 *
 * 集合一种实现：
 *   二分搜索树不能添加重复元素，是非常好的实现“集合”的底层数据结构
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/12
 */
public class BSTSet<E extends Comparable> implements Set<E> {

    private BSTSet<E> bst;

    private BSTSet(){
        bst = new BSTSet<>();
    }

    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }
    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }
    /**
     * 在二分搜索树平衡情况下
     * 时间复杂度为： O（logn）
     */
    @Override
    public void remove(E e) {
         bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
