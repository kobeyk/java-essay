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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
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


    /**
     * 基于key取缓存数据
     * @param key 缓存key
     * @return V 返回缓存数据
     */
    public V get(K key){
        // 1、先从map缓存中查找key对应的node
        Node node = cache.get(key);
        if(node == null){
            return null;
        }
        // 2、如果存在的话，干一件事情，把当前正在被使用的节点移动到头部
        moveToHead(node);
        return node.val;
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
            // 2.4 别忘了size+1
            size++;

            // 2.5 判断当前size是否超过最大capacity
            if(size > capacity){
                // 2.5.1 将尾部的节点移除掉
                Node tail = removeTail();
                // 2.5.2 链表移除后，Map中也移除下
                cache.remove(tail.key);
                // 2.5.3 别忘了，缓存数据的个数减1
                size--;
            }

        }else{
            // 3.如果node存在，替换下当前的值
            node.val = val;
            // 4.将当前node移动到头部
            moveToHead(node);
        }
    }

    public int size(){
        return cache.size();
    }

    /**
     * 从cache里遍历数据（不保证顺序）
     * @return String {a=1,b=2,....}
     */
    @Override
    public String toString() {
        // StringBuilder非线程安全，不考虑并发，使用这种方式对string操作效率快
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<K, Node> nodeEntry : cache.entrySet()) {
            Node node = nodeEntry.getValue();
            sb.append(nodeEntry.getKey())
                    .append("=")
                    .append(node.val)
                    .append(",");
        }
        return  sb.toString().substring(0,sb.lastIndexOf(","))+"}";
    }

    /**
     * 从当前双向链表中遍历节点
     * @return String {a=1,b=2,....}
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Node n = head.next ;
        // n必须是实际存储数据的节点，因此判断需要排除tail节点
        while(n!=null && n!=tail){
            sb.append(n.key)
                    .append("=")
                    .append(n.val)
                    .append(",");
            n=n.next;
        }
        return  sb.toString().substring(0,sb.lastIndexOf(","))+"}";
    }

    /**
     * 在头部添加一个node
     * @param node 缓存节点（数据）
     */
    public void addToHead(Node node){
        // 1、保存下头的后驱节点（即可以称作带有数据的链表中的第一个（first）节点）
        Node first = head.next;
        // 2、将头的后驱节点重定向到当前节点
        head.next = node;
        // 3、设置当前节点的前驱节点为头结点
        node.prev = head;
        // 4、设置当前节点的后驱节点为之前头节点的后驱节点
        node.next = first;
        // 5、设置first的前驱节点为当前节点(这一点一定不要忽略了！！！，否则会抛NPE)
        first.prev = node;
    }

    /**
     * 将node移动到头部
     * @param node 缓存节点（数据）
     */
    public void moveToHead(Node node){
        // 1、先重置当前节点的前后驱节点
        removeNode(node);
        // 2、在将当前节点添加到头部
        addToHead(node);
    }

    /**
     * 对当前node进行移动（移动的话，需要断开前驱和后驱的指向）
     * @param node 缓存节点（数据）
     */
    public void removeNode(Node node){
        // 1、当前节点的前驱节点的后驱节点指向其后驱节点
        node.prev.next = node.next;
        // 2、当前节点的后驱节点的前驱节点指向其前驱节点
        node.next.prev = node.prev;
    }

    /**
     * 移动最近最久未使用的节点，即尾节点！！！
     * @return Node 返回（被移除）的尾节点
     */
    public Node removeTail(){
        // 1、直接找到尾节点
        Node last = tail.prev;
        // 2、移动该节点
        removeNode(last);
        System.out.println("** 节点："+last+"被移除！ **");
        return last;
    }

    public static void main(String[] args) {
        LRUCache<String,Integer> lruCache = new LRUCache<>(5);
        lruCache.put("a",1);
        lruCache.put("b",2);
        lruCache.put("c",3);
        lruCache.put("d",4);
        lruCache.put("e",5);
        lruCache.get("a");//命中a，给a"续命"，将a放到链表的头部
        lruCache.put("f",6);//默认缓存容量是5，再添加一个数据，就需要触发LRU机制移除尾节点（b）了
        System.out.println("遍历Map  ："+lruCache);
        System.out.println("遍历双链表："+lruCache.list());

    }
}
