package com.appleyk.leetcode.手写LRU缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运，实现方式哈希Map+双向链表</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:05 下午 2020/10/13
 */
public class LRUCache<K, V> {

    /**默认容量*/
    private static final int DEFAULT_CAPACITY = 16;
    /**缓存容量，既缓存最大可承受的存储对象的个数*/
    private int capacity;
    /**缓存元素的大小*/
    private int size = 0;
    /**缓存元素被修改的次数*/
    private int modCount = 0;
    /**构建一个Map集合，主要用来基于key快速定位Node（缓存数据），时间查找复杂度O(1)*/
    private Map<K,Node> cache = new HashMap<>(capacity);
    /**定义头、尾节点，此处两个变量只是标识（指向），并不存储数据*/
    private Node head,tail;

    /**双向链表，新增、修改、删除的效率高，配合Map使用，空间换时间*/
    class Node {
        K key;
        V val;
        /**前驱结点*/
        Node prev;
        /**后驱节点*/
        Node next;
        public Node() {}
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(){
       this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        // 构建双向链表，头后驱节点指向尾，尾前驱节点指向头
        head.next = tail;
        tail.prev = head;
        this.getClass();
    }

    /***
     * 缓存数据
     * @param key 键
     * @param val 值
     */
    public void put(K key,V val){

        // 1.首先要先从cache中取key对应的Node
        Node node = cache.get(key);
        // 2.如果等于空的话，走创建，并put进cache
        if(node == null){
            // 2.1 创建新的节点
            node = new Node(key,val);
            // 2.2 放进去
            cache.put(key,node);
            // 2.3 把最近添加的(新增)node，添加到头部
            addToHead(node);
            // 2.4 size++
            size++;

            // 2.4 判断当前size是否超过最大capacity
            if(size > capacity){
                // 2.4.1 将尾部的节点移除掉
                Node tail = removeTail();
                // 2.4.2 链表移除后，Map中也移除下
                cache.remove(tail.key);
                // 2.4.3 别忘了，缓存数据的个数减1
                size--;
            }

        }else{
            // 3.如果node存在，替换下当前的值
            node.val = val;
            // 4.将当前node移动到头部
            moveToHead(node);
        }
    }

    /**
     * 在头部添加一个node
     * @param node
     */
    public void addToHead(Node node){

    }

    /**
     * 将node移动到头部
     * @param node
     */
    public void moveToHead(Node node){

    }

    /**
     * 对当前node进行移动（移动的话，需要断开前驱和后驱的指向）
     * @param node
     */
    public void removeNode(Node node){

    }

    /**
     * 移动最近最久未使用的节点，即尾节点！！！
     * @return
     */
    public Node removeTail(){
        return null;
    }
}
