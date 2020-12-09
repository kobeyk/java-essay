package com.appleyk.leetcode.链表;

/**
 * <p>越努力，越幸运</p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:36 下午 2020/12/5
 */
public class _206_反转链表 {

    // 1->2->3->4->5->null (递归方式)
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            // 如果没有节点，就是空，如果就一个节点的话，反转后还是自己
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            // 如果没有节点，就是空，如果就一个节点的话，反转后还是自己
            return head;
        }

        ListNode newHead = null;
        while(head!=null){
            // 1、首先保存下当前节点的后继节点
            ListNode temp = head.next;
            // 2、然后让当前节点的next指向newHead
            head.next = newHead;
            // 3、newHead指向head节点
            newHead = head;
            // 4、head节点在指向他的后继节点
            head = temp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        System.out.println(node);
        System.out.println("反转后");
        System.out.println(reverseList2(node));
    }
}
