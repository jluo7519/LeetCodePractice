public class DoublyLinkedListNode<T> {
    //fields
    private T val;
    private DoublyLinkedListNode prev;
    private DoublyLinkedListNode next;

    public DoublyLinkedListNode() {
        val = null;
        prev = null;
        next = null;
    }
    public DoublyLinkedListNode(T val) {
        this();
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public DoublyLinkedListNode getPrev() {
        return prev;
    }

    public boolean setPrev(DoublyLinkedListNode prev) {
        boolean ret = true;
        if (this.prev != null) {
            ret = false;
        }
        this.prev = prev;
        prev.next = this;
        return ret;
    }

    public DoublyLinkedListNode getNext() {
        return next;
    }

    public boolean setNext(DoublyLinkedListNode next) {
        boolean ret = true;
        if (this.next != null) {
            ret = false;
        }
        this.next = next;
        next.prev = this;
        return ret;
    }

    public boolean hasNext() {
        return this.next != null;
    }
    public boolean hasPrev() {
        return this.prev != null;
    }

    public static void main(String args[]) {
        DoublyLinkedListNode node1 = new DoublyLinkedListNode(1);
        DoublyLinkedListNode node2 = new DoublyLinkedListNode(2);
        DoublyLinkedListNode node3 = new DoublyLinkedListNode(3);
        node1.setNext(node2);
        //node2.setNext(node1);
        System.out.println(node1.hasNext());
        System.out.println(node2.hasPrev());
    }
}
