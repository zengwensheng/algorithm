package com.zws.example.struct.tree.uf;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/5/6
 *
 *  底层使用一个数组构建一棵指向父节点的树
 *
 *   优化：基于size的优化
 */
public class QuickUnion1 implements UF {


    private Integer[] parent;
    private Integer[] size;


    public QuickUnion1(int size) {
        this.parent = new Integer[size];
        this.size = new Integer[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.size[i] = i;
        }
    }

    @Override
    public Integer getSize() {
        return parent.length;
    }

    /**
     *  时间复杂度:O(logn)
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(Integer p, Integer q) {
        return find(p).compareTo(find(q))==0;
    }

    /**
     * 时间复杂度:O(logn)
     * @param index
     * @return
     */
    private Integer find(int index) {
        if (index < 0 || index > parent.length) {
            throw new IllegalArgumentException("index is out of bound.");
        }

        while(parent[index].compareTo(index)!=0){
            index = parent[index];
        }
        return index;
    }

    /**
     *  时间复杂度:O(logn)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(Integer p, Integer q) {
        Integer pValue = find(p);
        Integer qValue = find(q);

        if(pValue.compareTo(qValue)==0){
            return;
        }


        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if(size[pValue].compareTo(size[qValue])==1){
            parent[qValue] = pValue;
            size[qValue] += size[pValue];
        }else{
            parent[pValue] = qValue;
            size[pValue] += size[qValue];
        }

    }

    public static void main(String[] args) {
        UF uf = new QuickUnion1(10);
        uf.unionElements(4,3);
        uf.unionElements(3,8);
        uf.unionElements(6,5);
        uf.unionElements(9,4);
        uf.unionElements(2,1);
        uf.unionElements(5,0);
        uf.unionElements(7,2);
        uf.unionElements(6,2);
        System.out.println();
    }
}
