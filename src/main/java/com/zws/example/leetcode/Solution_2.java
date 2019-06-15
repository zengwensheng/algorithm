package com.zws.example.leetcode;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode retNode = null;
        ListNode cur = null;

        int i=0;

        while(l1!=null||l2!=null||i!=0){
            int l1Value = 0;
            int l2Value = 0;

            if(l1!=null){
                l1Value = l1.val;
                l1 = l1.next;
            }

            if(l2!=null){
                l2Value = l2.val;
                l2 = l2.next;
            }

            int sum = l1Value+l2Value+i;
            i = 0;
            if(sum>=10){
                sum %=10;
                i=1;
            }

            if(retNode==null){
                retNode = new ListNode(sum);
                cur = retNode;
            }else{
                cur.next = new ListNode(sum);
                cur = cur.next;
            }
        }
        return retNode;
    }

}
