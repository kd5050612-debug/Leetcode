/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int first = -1;
        int previousCritical = -1;

        ListNode previous = head;
        ListNode current = head.next;
        int index = 1;

        while(current != null && current.next != null){
            int prevValue = previous.val;
            int currValue = current.val;
            int nextValue = current.next.val;

            if((currValue > prevValue && currValue > nextValue) ||
            (currValue < prevValue && currValue < nextValue)){
                if(first == -1){
                    first = index;
                }
                if(previousCritical != -1){
                    minDistance = Math.min(
                        minDistance,
                        index - previousCritical
                    );
                }
                previousCritical = index;

            }
            previous = current;
            current = current.next;
            index++;
        }
        if(first == -1 || previousCritical == first){
            return new int[]{-1, -1};
        }
        int maxDistance = previousCritical - first;
        return new int[]{minDistance, maxDistance};
    }
}
