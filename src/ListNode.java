public class ListNode<T> {
    T val;
    ListNode prev;
    ListNode next;
    public ListNode() {

    }
    public ListNode(T val) {
        this();
        this.val = val;
    }
    public ListNode(T val, ListNode next) {
        this(val);
        this.next = next;
    }
}