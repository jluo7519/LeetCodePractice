import java.util.*;

public class LC00281ZigZagIterator {
    private Queue<Iterator<Integer>> queue;
    public LC00281ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (v1 != null) {
            Iterator<Integer> it1 = v1.iterator();
            if (it1.hasNext()) {
                queue.offer(it1);
            }
        }
        if (v2 != null) {
            Iterator<Integer> it2 = v2.iterator();
            if (it2.hasNext()) {
                queue.offer(it2);
            }
        }
    }
    public LC00281ZigZagIterator(List<List<Integer>> lists) {
        queue = new LinkedList<>();
        for (List cur : lists) {
            if (cur != null) {
                Iterator<Integer> it = cur.iterator();
                if (it.hasNext()) {
                    queue.offer(it);
                }
            }
        }

    }
    public int next() {
        Iterator<Integer> cur = queue.poll();
        int ret = cur.next();
        if (cur.hasNext()) {
            queue.offer(cur);
        }
        return ret;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

class SShapeIterator {
    private Stack<Iterator<Integer>> stack1; // forward
    private Stack<Iterator<Integer>> stack2; // backward

    public SShapeIterator(List<List<Integer>> lists) {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        for (int i = lists.size() - 1; i >= 0; i--) {
            List<Integer> list = lists.get(i);
            if (list != null) {
                Iterator<Integer> it = list.iterator();
                if (it.hasNext()) {
                    stack1.push(it);
                }
            }
        }
    }
    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("no next");
        }
        // stack 1 empty means forward round is over, now backwards
        if (stack1.isEmpty()) {
            Stack<Iterator<Integer>> tmp = stack1;
            stack1 = stack2;
            stack2 = tmp;
        }
        Iterator<Integer> cur = stack1.pop();
        int ret = cur.next();
        if (cur.hasNext()) {
            stack2.push(cur);
        }
        return ret;
    }
    public boolean hasNext() {
        return !stack1.isEmpty() || !stack2.isEmpty();
    }
}