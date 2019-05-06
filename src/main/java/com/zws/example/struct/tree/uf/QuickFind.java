package com.zws.example.struct.tree.uf;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/6
 */
public class QuickFind implements UF {


    private Integer[] data;


    public QuickFind(int size) {
        data = new Integer[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    /**
     * 时间复杂度 O(1)
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p).compareTo(find(q))==0;
    }

    @Override
    public void unionElements(int p, int q) {
        Integer qValue = find(q);
        for (int i = 0; i < data.length; i++) {
            if (data[i].compareTo(qValue) == 0) {
                data[i] = find(p);
            }
        }
    }


    private Integer find(Integer index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is out of bound.");
        }
        return data[index];
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
