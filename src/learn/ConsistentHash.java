package learn;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

// implementation of ConsistentHash
// https://en.wikipedia.org/wiki/Consistent_hashing
// https://www.toptal.com/big-data/consistent-hashing


public class ConsistentHash<T> {

    class HashFunction {
        public int hash(Object key) {
            // md5 hash
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            md5.reset();
            md5.update(((String)key).getBytes());
            byte[] bKey = md5.digest();
            int res = 0;
            for (byte b : bKey) {
                res <<= 8;
                res |= (b & 0xFF);
            }
            return res;
        }
    }

    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<>();

    public void listCircle() {
        System.out.println("listCircle");
        for (Integer key : circle.keySet()) {
            System.out.println(key + " " + circle.get(key));
        }
    }

    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = new HashFunction();
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            // tailMap returns a view of the portion of this map whose keys are greater than or equal to fromKey.
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            // firstKey returns the first (lowest) key currently in this map.
            // if tailMap is empty, then return the first key of circle
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // test this class
        // create a ConsistentHash object, with 3 replicas per node and 3 nodes
        ConsistentHash<String> cHashRouter = new ConsistentHash<String>(3, Collections.emptyList());

        // add some nodes
        cHashRouter.add("node1");
        cHashRouter.listCircle();
        cHashRouter.add("node2");
        cHashRouter.listCircle();
        cHashRouter.add("node3");
        cHashRouter.listCircle();

        // get the node for a key
        System.out.println("===================================");
        for (int i = 0; i < 10; i++) {
            System.out.printf("get node %s for key %s %n", cHashRouter.get("key" + i), "key" + i);
        }
        System.out.println("===================================");

        // remove a node
        cHashRouter.remove("node1");
        System.out.println("after remove a node1");
        cHashRouter.listCircle();
        for (int i = 0; i < 10; i++) {
            System.out.printf("get node %s for key %s %n", cHashRouter.get("key" + i), "key" + i);
        }

        System.out.println("===================================");
        System.out.println("after add node1 back");

        // add node 1
        cHashRouter.add("node1");
        cHashRouter.listCircle();
        for (int i = 0; i < 10; i++) {
            System.out.printf("get node %s for key %s %n", cHashRouter.get("key" + i), "key" + i);
        }

        System.out.println("===================================");
        System.out.println("after add node 4");
        // add node 4
        cHashRouter.add("node4");
        cHashRouter.listCircle();
        for (int i = 0; i < 10; i++) {
            System.out.printf("get node %s for key %s %n", cHashRouter.get("key" + i), "key" + i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.printf("get node %s for key %s %n", cHashRouter.get("key" + i), "key" + i);
        }
    }
}
