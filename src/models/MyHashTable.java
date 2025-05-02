public class MyHashTable<K, V> {

    private class HashNode<K, V> {//represent 1 entry where each node stores a key and a value +reference to the next node in the chain
        private K key;
        private V value;
        private HashNode<K, V> next;

        //constructor
        public HashNode(K key, V value) {//constructor that initialize node with key and value
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " = " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray; //creating an array (linked list) that stores entries of the hash table
    private int M = 11;
    private int size;

    //constructor that allows to set
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) { //methods that generates an ind between 0 and M-1
        return (key.hashCode() & 0x7fffffff) % M;
        //this is the shortened ver , if we expand it its like
        //int tempInd=key.hashCode()'
        //int PositiveSign=Math.and(temp);
        //return PositiveSign%M;
    }

    public void put(K key, V value) {//creating a method that adds or updated a key-value pair
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; //HERE WE ARE UPDATING AN EXISTING KEY
                return;
            }
            head = head.next;
        }

        //here we are inserting at beginning
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[bucketIndex];
        chainArray[bucketIndex] = newNode;
        size++;
    }

    public V get(K key) {//method that retrieves the value for a given key
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {//method that removed a key-value pair + returns that value
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null)
                    prev.next = head.next;
                else
                    chainArray[bucketIndex] = head.next;
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean contains(V value) {//here we cheack if a value in the table
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value))
                    return true;
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {//returns a key for a given value
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value))
                    return head.key;
                head = head.next;
            }
        }
        return null;
    }
}
