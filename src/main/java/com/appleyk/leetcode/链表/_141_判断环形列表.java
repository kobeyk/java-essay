package com.appleyk.leetcode.链表;

/**
 * <p>越努力，越幸运</p>
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 解决思路：快慢指针（如果有环的话，快的肯定能追上慢的，即有一时刻，两个肯定会相遇（等））
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:36 下午 2020/12/5
 */
public class _141_判断环形列表 {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        // 快指针（跑的快的）
        ListNode fast = head.next;
        // 慢指针（跑的慢的）
        ListNode slow = head;

        // 一旦fast等于null或者fast的next等于null，说明链表到头了，否者就跳出循环，直接返回false了
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node3;
        System.out.println("是否有环："+hasCycle(node1));
    }
}
