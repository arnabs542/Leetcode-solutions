package algorithms.heap;
/**
 * @author Qihao He
 * @date 09/24/2019
 * https://app.laicode.io/app/problem/134
 * Assumptions: listOfLists is not null, none of the lists is null.
 */

import algorithms.linkedList.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class MergeKSortedLists {
    public ListNode merge(List<ListNode> listOfLists) {
        // Write your solution here/.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new MyComparator());
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (ListNode node : listOfLists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        while (!minHeap.isEmpty()) {
            cur.next = minHeap.poll();
            if (cur.next.next != null) {
                minHeap.offer(cur.next.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }

}
class MyComparator implements Comparator<ListNode> {
    @Override
    public int compare(ListNode o1, ListNode o2) {
        if (o1.value == o2.value) {
            return 0;
        }
        return o1.value < o2.value ? -1 : 1;
    }
}
