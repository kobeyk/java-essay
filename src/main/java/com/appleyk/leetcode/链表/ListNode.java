package com.appleyk.leetcode.链表;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:22 下午 2020/12/5
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x){this.val = x;}

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        ListNode head = this;
        if(head == null){return "null";}
        sb.append(head.val);
        while(head.next!=null){
            sb.append("->"+head.next.val);
            head = head.next;
        }
        return sb.toString()+"->null";
    }
}
