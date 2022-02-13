import java.util.ArrayList;
import java.util.List;

public class RouterLongestPrefixMatching {
    public static final int DEFAULT_PORT = 8;
    private RoutingNode root;

    public RouterLongestPrefixMatching() {
        root = new RoutingNode(false); // dummy
    }
    public void build(List<Entry> entries) {
        for (Entry entry : entries) {
            addEntry(entry);
        }
    }
    public int route(boolean[] packet) {
        int port = DEFAULT_PORT;
        RoutingNode cur = root;
        for (int i = 0; i < packet.length; i++) {
            int idx = packet[i] == false ? 0 : 1;
            if (cur.nexts[idx] == null) {
                return port;
            }
            cur = cur.nexts[idx];
            port = cur.port;
        }
        return port;
    }

    private void addEntry(Entry entry) {
        RoutingNode cur = root;
        boolean[] prefix = entry.prefix;
        for (int i = 0; i < prefix.length; i++) {
            int idx = prefix[i] == false ? 0 : 1;
            if (cur.nexts[idx] == null) {
                cur.nexts[idx] = new RoutingNode(prefix[i]);
            }
            cur = cur.nexts[idx];
        }
        cur.port = entry.port;
    }

    public static void main(String args[]) {
        RouterLongestPrefixMatching test = new RouterLongestPrefixMatching();
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(new boolean[]{false,false,false}, 3));
        entries.add(new Entry(new boolean[]{true,false}, 4));
        entries.add(new Entry(new boolean[]{true}, 5));

        test.build(entries);
        System.out.println(test.route(new boolean[]{false,false,false,true,false})); // 3
        System.out.println(test.route(new boolean[]{true,false,true,false})); // 4
        System.out.println(test.route(new boolean[]{true,false,false,true})); // 4
        System.out.println(test.route(new boolean[]{true,true,false})); // 5
        System.out.println(test.route(new boolean[]{true, true, true})); // 5
        System.out.println(test.route(new boolean[]{false,false,true,false,true,false})); // 8
    }
}
class Entry {
    boolean[] prefix;
    int port;

    public Entry(boolean[] prefix, int port) {
        this.prefix = prefix;
        this.port = port;
    }
}

class RoutingNode {
    boolean val;
    RoutingNode[] nexts;
    int port;

    public RoutingNode(boolean val) {
        this.val = val;
        this.nexts = new RoutingNode[2];
        port = 8;
    }
}
