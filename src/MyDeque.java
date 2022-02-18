public class MyDeque<T> {
    private ListNode<T> head; // first
    private ListNode<T> tail; // last
    private int size;
    public MyDeque(){
        this.head = new ListNode<T>(); // dummy
        this.tail = new ListNode<T>(); // dummy
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }
    public void offerFirst(T item) {
        addNode(this.head, item, this.head.next);
    }
    public void offerLast(T item) {
        addNode(tail.prev, item, tail);
    }
    public T pollFirst() {
        return (T)removeNode(head.next);
    }
    public T pollLast() {
        return (T)removeNode(tail.prev);
    }
    public T peekFirst() {
        if (this.size == 0) {
            return null;
        }
        return (T)head.next.val;
    }
    public T peekLast() {
        if (this.size == 0) {
            return null;
        }
        return (T)tail.prev.val;
    }
    private void addNode(ListNode<T> prev, T item, ListNode<T> next) {
        ListNode<T> cur = new ListNode<>(item);
        prev.next = cur;
        cur.prev = prev;
        cur.next = next;
        next.prev = cur;
        this.size++;
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    private T removeNode(ListNode<T> removeNode) {
        if (this.size == 0) return null;
        ListNode<T> prev = removeNode.prev;
        ListNode<T> next = removeNode.next;
        prev.next = next;
        next.prev = prev;
        removeNode.prev = null;
        removeNode.next = null;
        this.size--;
        return removeNode.val;
    }

    public static void main(String args[]) {
        MyDeque<Integer> deque = new MyDeque<>();
        System.out.println("size " + deque.size());
        deque.offerFirst(1);
        deque.offerLast(2);
        System.out.println("size " + deque.size());
        deque.offerLast(3);
        System.out.println(deque.peekFirst());
        System.out.println("size " + deque.size());
        System.out.println(deque.pollFirst());
        System.out.println("size " + deque.size());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        System.out.println("size " + deque.size());
    }
}
