package com.zws.example.struct.tree.uf;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/6
 * <p>
 * 底层使用一个数组构建一棵指向父节点的树
 * <p>
 * 优化：基于rank的优化 (rank 是树的高度) ,路径压缩
 */
public class QuickUnion3 implements UF {


    private Integer[] parent;
    private Integer[] rank;


    public QuickUnion3(int size) {
        this.parent = new Integer[size];
        this.rank = new Integer[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = i;
        }
    }

    @Override
    public Integer getSize() {
        return parent.length;
    }

    /**
     * 时间复杂度:O(logn)
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(Integer p, Integer q) {
        return find(p).compareTo(find(q)) == 0;
    }

    /**
     * 时间复杂度:O(logn)
     *
     * @param index
     * @return
     */
    private Integer find(int index) {
        if (index < 0 || index > parent.length) {
            throw new IllegalArgumentException("index is out of bound.");
        }

        while (parent[index].compareTo(index) != 0) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    /**
     * 时间复杂度:O(logn)
     *
     * @param p
     * @param q
     */
    @Override
    public void unionElements(Integer p, Integer q) {
        Integer pValue = find(p);
        Integer qValue = find(q);

        if (pValue.compareTo(qValue) == 0) {
            return;
        }

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if (rank[pValue].compareTo(rank[qValue]) == 1) {
            parent[qValue] = pValue;
        } else if (rank[pValue].compareTo(rank[qValue]) == -1) {
            parent[pValue] =   qValue;
        } else {
            parent[qValue] = pValue;
            rank[pValue] += 1;
        }

    }

    public static void main(String[] args) {
        UF uf = new QuickUnion3(10);
        uf.unionElements(4, 3);
        uf.unionElements(3, 8);
        uf.unionElements(6, 5);
        uf.unionElements(9, 4);
        uf.unionElements(2, 1);
        uf.unionElements(5, 0);
        uf.unionElements(7, 2);
        uf.unionElements(6, 2);
        System.out.println();
    }
}
