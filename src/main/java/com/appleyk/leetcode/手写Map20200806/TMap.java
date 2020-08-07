package com.appleyk.leetcode.手写Map20200806;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class TMap<K,V> implements MyMap<K,V>{

    /**加载因子*/
    final float loadFactory = 0.75f;
    /**默认数组长度*/
    int defaultCapacity = 16;
    /**节点数组*/
    Node[] table;
    int size ;

    TMap(){
        table = new Node[defaultCapacity];
    }

    TMap(int capacity){
        this.defaultCapacity = capacity;
        table = new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K k, V v) {

        // 如果key是null，永远放到第一位，HashMap是支持key是null的，Hashtable不支持
        if(k == null){
            Node<K,V> oldNode = table[0];
            table[0] = new Node(null,v);
            return oldNode.getValue();
        }

        // 否者的话，得通过key值计算hash值，主要是为了找key在table数组中的下标索引
        int index = getIndex(k);
        // 先把原有该索引处的node保存一下
        Node<K,V> oldNode = table[index];
        // 然后将现有的node放上去
        Node newNode = new Node(k,v);
        table[index] = newNode;
        /**设置下一个节点*/
        newNode.setNext(oldNode);
        return oldNode!=null ? oldNode.getValue() : null;
    }


    /**通过key计算得出其所在的node在table中的哪一个下标处*/
    private int getIndex(K k){
        // 一个key的hashCode 与 数组长度-1的值进行位与运算，永远不会在[0,defaultCapacity-1]之间
        return k.hashCode() & (defaultCapacity-1);
    }

    @Override
    public V get(K k) {
        return null;
    }

    class Node<K,V> implements MyMap.Entry<K,V>{

        K k;
        V v;
        Node<K,V> next;

        Node(K k ,V v){
            this.k = k ;
            this.v = v ;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V v) {
            this.v = v;
            return v;
        }

        @Override
        public Entry<K, V> next() {
            if(next!=null){
                return this.next;
            }
            return null;
        }

        @Override
        public void setNext(Entry<K, V> node) {
            this.next = (Node<K, V>) node;
        }
    }
}
