public class MyStack<T> {
    private ListNode<T> head;
    private int size;
    public MyStack() {
        this.head = null;
        this.size = 0;
    }
    public void push(T item) {
        ListNode<T> newNode = new ListNode<T>(item);
        newNode.next = head;
        head = newNode;
        this.size++;
    }
    public T pop() {
        if (head == null) {
            return null;
        }
        ListNode<T> top = this.head;
        this.head = this.head.next;
        top.next = null;
        this.size--;
        return top.val;
    }
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.val;
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}