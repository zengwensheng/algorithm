package com.zws.example.struct.tree;

@FunctionalInterface
public interface Merger<E> {

     E merge(E a,E b);
}
