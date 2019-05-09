package com.zws.example.struct.tree.uf;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/6
 *
 *  QuickFind下的find 时间复杂度 0(1)
 *  QuickFind下的union 时间复杂度 0(n)
 *  底层数据结构使用数组实现
 *
 */
public class QuickFind implements UF {


    private Integer[] id;


    public QuickFind(int size) {
        this.id = new Integer[size];
        for (int i = 0; i < size; i++) {
            this.id[i] = i;
        }
    }

    @Override
    public Integer getSize() {
        return id.length;
    }

    /**
     * 时间复杂度 O(1)
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(Integer p, Integer q) {
        return find(p).compareTo(find(q))==0;
    }

    /**
     * 时间复杂度 O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(Integer p, Integer q) {
        Integer qValue = find(q);
        for (int i = 0; i < id.length; i++) {
            if (id[i].compareTo(qValue) == 0) {
                id[i] = find(p);
            }
        }
    }


    private Integer find(Integer index) {
        if (index < 0 || index >= id.length) {
            throw new IllegalArgumentException("index is out of bound.");
        }
        return id[index];
    }


    public static void main(String[] args) {
        UF uf = new QuickFind(10);
        int p = 2,q = 5;
        System.out.println(uf.isConnected(p,q));
        uf.unionElements(p,q);
        System.out.println(uf.isConnected(p,q));

        p = 4;
        q = 5;
        System.out.println(uf.isConnected(p,q));
        uf.unionElements(p,q);
        System.out.println(uf.isConnected(p,q));

    }
}
