package com.appleyk.leetcode.手写Map20200806;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     越努力，越幸运 实现Map接口，仿写JDK1.7版本的HashMap功能
 *     1、非线程安全
 *     2、利用hash+数组+链表来实现 key-value的存取
 *     3、当存放的元素庞大时，会导致map的put和get效率会变低，两方面原因
 *        3.1 没有指定capacity的大小，使用了默认的16，导致扩容（reSize+reHash）频繁，put效率降低
 *        3.2 元素过多，在数组容量有限的情况下，key值进行hash计算得到的index值出现相同的概率增大，
 *            导致index下标存放的链表长度过长，getValue的时候，增加了遍历的次数
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class TMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTORY = 0.75f;

    /**节点数组，Node实现了Entry接口，主要就是放key和value的*/
    Node<K, V>[] table;

    /**加载因子，和capacity值配合起来，可以决定table是否要扩容*/
    private float loadFactory;

    /**table数组容量(长度)*/
    private volatile int capacity;

    /**Node元素个数*/
    int size;

    /**门面模式，虽然是两个构造器，其实指向的是一个*/
    TMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTORY);
    }

    TMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTORY);
    }

    TMap(int capacity, float loadFactory) {
        this.capacity = capacity;
        this.loadFactory = loadFactory;
        table = new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K k, V v) {

        // 再开始插入新node的时候，先判断下，是否有必要对table进行动态扩容
        if (size >= capacity * loadFactory) {
            // 扩容，默认扩容的临界值是元素的个数>=12
            resize();
        }

        // 如果key是null，永远放到第一位，HashMap是支持key是null的，Hashtable不支持
        if (k == null) {
            Node<K, V> oldNode = table[0];
            table[0] = new Node(null, v);
            return oldNode.getValue();
        }

        // 如果，得通过key值计算hash值，主要是为了找key在table数组中的下标索引
        int index = getIndex(k);
        // 先把原有该索引处的node保存一下
        Node<K, V> oldNode = table[index];
        // 循环判断下，k值是不是存在了，如果存在了，就要更新对应entry的value
        Node<K, V> node = oldNode;
        while (node != null) {
            if (k.equals(node.getKey())) {
                node.setValue(v);
                return node.getValue();
            }
            node = node.next;
        }
        // 然后将现有的node放进去
        Node newNode = new Node(k, v);
        table[index] = newNode;
        /**设置下一个节点*/
        newNode.setNext(oldNode);
        ++size;
        return oldNode != null ? oldNode.getValue() : null;
    }

    /***
     * 1、重新生成一个newTable，长度为当前数组容量*2
     * 2、重新计算key的hash值，将原有旧表中的entry节点打散放到新表中
     */
    private void resize() {
        long start = System.currentTimeMillis();
        System.out.println("=== 发生了一次扩容 ===");
        // 默认扩容*2，就原来默认是16的话，扩容后数组的length就是32
        this.capacity *=2;
        // 记住，这个size别漏掉了，要重新归0，否则不仅会增加扩容的次数，还会导致最终的元素个数不对
        size = 0;
        reHash(new Node[capacity*2]);
        long end = System.currentTimeMillis();
        System.out.println("扩容耗时："+(end-start)+"ms");
    }

    private void reHash(Node<K,V>[] newTable){
        // 1、遍历原来的table中的entry，将所有的entry记录下来
        List<Node<K,V>> allNodes = new ArrayList<>();
        for (int i = 0; i < table.length ; i++) {
            Node<K, V> node = table[i];
            while( node != null){
                allNodes.add(node);
                node = node.next;
            }
        }

        // 2、将新表赋给旧表，也就是table指向新的内存地址
        this.table = newTable;

        // 3、重新打散收集到的所有的entry，其实就是调用类中的put方法
        // 4、将旧entry节点添加到新数组中，原有位置上的链表会倒置
        for (Node<K, V> node : allNodes) {
            put(node.getKey(),node.getValue());
        }

        // GC回收
        newTable = null;
    }

    /**
     * 通过key计算得出其所在的node在table中的哪一个下标处
     */
    private int getIndex(K k) {
        // 一个key的hashCode 与 数组长度-1的值进行位与运算，永远会在[0,defaultCapacity-1]之间
        return k.hashCode() & (capacity - 1);
    }

    @Override
    public V get(K k) {

        // 取的时候，就就有讲究了，你要先判断key是不是null，null的话，直接返回第一个元素
        if (k == null) {
            return table[0].getValue();
        }

        // 否则的话，和put一样，先进行hash值，算出index的值
        int index = getIndex(k);

        // 确定了index的位置后，判断下，有没有值，也就是虽然hash算出了index，但那个位置不一样有entry啊
        Node<K, V> node = table[index];
        if (node == null) {
            return null;
        }
        // 如果有的话，那就好办了，循环该位置上的链表，判断key是哪一个entry对象的，找到他，拿出他的value值即可
        while (node != null) {
            if (k.equals(node.getKey())) {
                return node.getValue();
            }
            node = node.next;
        }
        return null;
    }

    class Node<K, V> implements MyMap.Entry<K, V> {

        K k;
        V v;
        Node<K, V> next;

        Node(K k, V v) {
            this.k = k;
            this.v = v;
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

        public Entry<K, V> next() {
            if (next != null) {
                return this.next;
            }
            return null;
        }

        public void setNext(Entry<K, V> node) {
            this.next = (Node<K, V>) node;
        }
    }
}
