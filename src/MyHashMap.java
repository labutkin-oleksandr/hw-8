import java.util.Objects;

public class MyHashMap <K, V> {
    private Node last;
    private int N = 0;
    private MyLinkedList<Node>[] table;

    public MyHashMap() {
        this.table = new MyLinkedList[16];
        for(int i = 0; i < table.length; i++) {
            this.table[i] = new MyLinkedList<>();
        }
    }

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    private static class Node<K, V> {
        int hash;
        private K key;
        private V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private static class Key <K> {
        K key;

        public Key (K key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key point = (Key) o;
            return key == point.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public boolean checkIfArrNode(Node checkNode, Node newNode) {
        if(checkNode.getHash() == newNode.getHash() && (checkNode.getKey() == newNode.getKey() || newNode.getKey().equals(checkNode.getKey()))) {
            return true;
        }

        return false;
    }

    public boolean put(K key, V value) {
        if(key == null) {
            return false;
        }

        Key<K> newKey = new Key<K>(key);
        int hash = hash(newKey.hashCode());
        int index = indexFor(hash, 16);
        int sizeBucket = table[index].size();

        Node<K,V> e = new Node<>(newKey.hashCode(), key, value, last);

        if(sizeBucket != 0) {
            for(int i = 0; i < sizeBucket; i++) {
                if(checkIfArrNode(table[index].get(i), e)) {
                    return false;
                }
            }
        }

        addEntry(e, index);
        N++;
        return true;
    }

    public void addEntry(Node<K,V> e, int index) {
        table[index].add(e);
        last = e;
    }

    public void remove(K key) {
        Key<K> newKey = new Key<K>(key);
        int hash = hash(newKey.hashCode());
        int index = indexFor(hash, 16);
        int sizeBucket = table[index].size();

        if(sizeBucket != 0) {
            for(int i = 0; i < sizeBucket; i++) {
                if(table[index].get(i).getHash() == newKey.hashCode()) {
                    table[index].remove(i);
                    N--;
                    return;
                }
            }
        }
    }

    public void clear() {
        if(N == 0) {
            return;
        }

        for(int i = 0; i < table.length; i++) {
            if(table[i].size() > 0) {
                table[i].clear();
            }
        }
        N = 0;
    }

    public int size() {
        return N;
    }

    public V get(K key) {
        Key<K> newKey = new Key<K>(key);
        int hash = hash(newKey.hashCode());
        int index = indexFor(hash, 16);
        int sizeBucket = table[index].size();

        if(sizeBucket != 0) {
            for(int i = 0; i < sizeBucket; i++) {
                if(table[index].get(i).getHash() == newKey.hashCode()) {
                    return (V) table[index].get(i).getValue();
                }
            }
        }
        return null;
    }
}
