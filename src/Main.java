import models.BST;
import models.MyTestingClass;
import models.MyHashTable;


public class Main {
    public static void main(String[] args) {
        // --- Testing MyHashTable ---
        System.out.println("=== Testing MyHashTable ===");
        MyHashTable<MyTestingClass, String> hashTable = new MyHashTable<>(11);

        MyTestingClass key1 = new MyTestingClass(1);
        MyTestingClass key2 = new MyTestingClass(2);
        MyTestingClass key3 = new MyTestingClass(3);

        hashTable.put(key1, "One");
        hashTable.put(key2, "Two");
        hashTable.put(key3, "Three");

        System.out.println("Get key2: " + hashTable.get(key2));
        System.out.println("Contains 'Two': " + hashTable.contains("Two"));
        System.out.println("Get key for value 'Three': " + hashTable.getKey("Three"));
        System.out.println("Remove key1: " + hashTable.remove(key1));
        System.out.println("Get key1 after removal: " + hashTable.get(key1));

        // --- Testing BST ---
        System.out.println("\n=== Testing BST ===");
        BST<Integer, String> bst = new BST<>();
        bst.put(50, "Root");
        bst.put(30, "Left");
        bst.put(70, "Right");
        bst.put(20, "Left.Left");
        bst.put(40, "Left.Right");
        bst.put(60, "Right.Left");
        bst.put(80, "Right.Right");

        System.out.println("Get 70: " + bst.get(70));
        System.out.println("Get 20: " + bst.get(20));
        System.out.println("Size before delete: " + bst.size());
        bst.delete(30);
        System.out.println("Size after delete: " + bst.size());
        System.out.println("In-order traversal:");
        for (BST.Entry<Integer, String> entry : bst.iterator()) {
            System.out.println(entry.getKey() + " => " + entry.getVal());
        }
    }
}
