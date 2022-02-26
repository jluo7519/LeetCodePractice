import java.util.Iterator;
import java.util.List;

public class DedupIterator {
    private Integer buffer;
    private List<Integer> list;
    private Iterator<Integer> it;
    public DedupIterator(List<Integer> list) {
        this.buffer = null;
        this.list = list;
        it = list.iterator();
    }

    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("no next");
        }
        Integer cur = null;
        if (buffer == null) { // 说明之前的不是重复
            cur = it.next();
        } else {             // 之前是重复, 所以越过了一个
            cur = buffer;    // 把之前越过的assign过来和后面比较 因为buffer是之前重复后的第一个数 有可能后面自己也有重复
            buffer = null;   // 如果buffer里是最后一个element的话 不reset会影响hasNext()判断
        }

        while (it.hasNext()) {
            int val = it.next(); // 如果重复就跳过
            if (val != cur) { // 找到第一个非重复element后break
                buffer = val;
                break;
            }
        }
        return cur;
    }

    public boolean hasNext() {
        return buffer == null && !it.hasNext();
    }
}
