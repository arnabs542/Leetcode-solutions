package algorithms.linkedlist;/** * @author Qihao He * @date 09/24/2019 * https://app.laicode.io/app/problem/36 * time complexity: fast and slow pointer traverse, O(1.5n) * space complexity: simple if else, O(1) *//** * class ListNode { *   public int value; *   public ListNode next; *   public ListNode(int value) { *     this.value = value; *     next = null; *   } * } */public class MiddleNodeOfLinkedList {	public ListNode middleNode(ListNode head) {		// Write your solution here		if (head == null || head.next == null) {			return head;		}		ListNode slow = head;		ListNode fast = head;		while (fast.next != null && fast.next.next != null) {			slow = slow.next;			fast = fast.next.next;		}		return slow;	}}