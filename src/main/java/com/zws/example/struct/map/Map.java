package com.zws.example.struct.map;

/**
 * 映射 Map
 *    存储（键，值） 数据对的数据结构（key,Value）
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/25
 */
public interface Map<K, V> {

    void put(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    int getSize();

    boolean isEmpty();

}
