package com.appleyk.leetcode.LRU算法实现;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>基于JDK现有的LinkedHashMap类，写一个LRU（最近最久未使用）机制的缓存容器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 13:03 2020/10/13
 */
public class LRUCache extends LinkedHashMap<String,Integer>{

    private int maxSize;

    public LRUCache(int maxSize){
        super(maxSize,0.75f,true);
        // 注意，这个赋值一定不要落下，否则你会发现put的时候，一直put空（因为，插入的节点总是会移除，卖个关子）
        this.maxSize = maxSize;
    }

    @Override
    public Integer get(Object key) {
        // 当取不到key对应的节点时，返回默认值-1
        return super.getOrDefault(key,-1);
    }


    /**
     * 这个方法在执行put操作时，会触发链表中的头结点的删除
     * 调用链：
     * 1、HashMap#put(k,v)
     * 2、HashMap#putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict)
     * 3、HashMap#afterNodeInsertion(evict);
     * 4、HashMap中为LinkedHashMap预留了三个方法
     * 备注:下面的注释很明确了，就是专门预留给LinkedHashMap进行回调的
     * // Callbacks to allow LinkedHashMap post-actions
     * void afterNodeAccess(Node<K,V> p) { // 在节点操作之后调用，方法作用：move node to last}
     * void afterNodeInsertion(boolean evict) { // 在节点插入之后调用，方法作用：possibly remove eldest}
     * void afterNodeRemoval(Node<K,V> p) { // 在节点移除之后调用，方法作用：消除当前节点的链接}
     * 其中afterNodeInsertion(evict)在LinkedHashMap中被实现，实现方式如下：
     * LinkedHashMap#void afterNodeInsertion(boolean evict) {// possibly remove eldest}
     * 注释很明确，就是可能会移除最近最少使用的节点（Entry）
     * 为什么说是可能呢，因为不确定是否要移除，是有条件的，接着往下看
     * LinkedHashMap#
     *  if (evict && (first = head) != null && removeEldestEntry(first)) {
     *        K key = first.key;
     *        removeNode(hash(key), key, null, false, true);
     *  }
     *  evict已经肯定是true（传过来就是），其次如果链表中头结点不为空的话，则
     *  如果LinkedHashMap#removeEldestEntry这个方法返回的是true的话，那就removeNode掉当前的头节点
     *  所以，我们要想基于LinkedHashMap实现一个LRU算法的话，就要复写该方法，当缓存容量满的时候要清除
     *  那些最近使用最少的entry了，对于LinkedHashMap来说，是移除头结点c
     *  这里有个细节需要注意，那就是一定要设置accessOrder = true，否则get的时候，无法将访问的当前节点放到链表的尾部
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return size() > maxSize;
    }

    public static void main(String[] args) {

        /**第三个参数 accessOrder，访问顺序，如果true的话， 基于访问顺序来，否则，基于插入顺序*/
        Map<String,Integer> link = new LinkedHashMap<>(6,0.75f,true);
        link.put("a",1);
        link.put("b",2);
        link.put("c",3);
        link.put("d",4);
        System.out.println(link);
        link.get("b");
        System.out.println(link);

        System.out.println("=================== 基于LinkedHashMap，实现LRU缓存 =====================");

        LRUCache cache = new LRUCache(2);
        cache.put("1",1); // 缓存节点1
        cache.put("2",2); // 缓存节点2
        System.out.println("不访问节点1时，链表的输出："+cache);// {1,2}
        cache.get("1");// 访问1，则会将1节点重置（重新安放）到链表的尾部
        System.out.println("访问节点1时，链表的输出:"+cache);// 再打印的时候，此时{2,1}
        // 缓存节点3，因为cache的容量最大就是2，超过这个值后，removeEldestEntry的值为true
        // 就要触发其中afterNodeInsertion方法中的removeNode方法了，该方法将移除头结点
        // 因为，当accessOrder = true时，是按照访问顺序来的，也就是访问节点时，就会触发将节点重置到链表尾部的操作
        // 所以，当cache的容量满时，再put节点时，会触发两个操作，一个就是将当前节点插入到链表尾部，一个就是将头节点删除
        cache.put("3",3);
        System.out.println(cache.get("3") == -1 ? "找不到了":"找到了，value = "+cache.get("3"));
        System.out.println(cache.get("2") == -1 ? "找不到了":"找到了，value = "+cache.get("3"));
        System.out.println(cache.get("1") == -1 ? "找不到了":"找到了，value = "+cache.get("1"));
    }

}
