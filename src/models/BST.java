package models;

import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable <K>, V> {
    private Node root;


    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private Node root;
    private int size=0;

    public int size(){
        return size;
    }
    

    public void put(K key, V val) {
        //first situation if the key is already exists
        Node newNode=new Node(key,val); //создаем newNode и вносим values (key and val)
        if(root==null){ //далее просто проверка если место пустует то -->
            root=newNode;//теперь тут будет значение newNode
            size++; //увеличиваем размер тк добавили узел

        }
        //but if the tree is empty, we need to create the 1st node as the root
        Node current=root;
        while(true){
            int temp=key.compareTo(current.key); //create a temp that do a comparison for the later placement
            if(temp==0){//if key exists, just update the val + return
                current.val=val;
                return;
            }
            else if(temp<0){ //if the key is smaller-> place it on the left side(only if its null)
                if(current.left==null){
                    current.left=newNode;
                    size++;
                    return;
                }
                current=current.left;
            }
            else{
                if(current.right==null){ //if the key is greater-> place it on the right side(only if its null)
                    current.right=newNode;
                    size++;
                    return;
                }
                current=current.right;
            }
        }



    }
    public V get(K key) { //searching a neccessery val by the key
        Node current =root;
        while(current!=null){
            int temp=key.compareTo(current.key);
            if(temp==0){
                return current.val;
            }
            else if(temp<0){
                current=current.left;


            }
            else{
                current=current.right;
            }
        }
        return null;
    }

    public void delete(K key){
        root=myDelete(root,key);

    }
    private Node myDelete(Node root, K key){ //here we are deleting the root based on the given key bruh
        Node parent=null; //create the parent just to manage the sources?? like left and right
        Node current=root; //the current one

        while(current!=null && !key.equals(current.key)){ //searching the node nased on the given key
            parent=current;
            int temp=key.compareTo(current.key);
            if(temp<0){
                current=current.left; //if we found it, point on it
            }
            else if(temp>0){
                current=current.right;//do the same
            }
        }
        if(current==null){ //if we didnt find the node in terms of the given key
            return root;//then just return the root without any changes
        }
        if(current.left==null || current.right==null){ //deleting the root witj onlu 1 child or none
            Node newCurrent=(current.left!=null) ? current.left: current.right;
            if(parent==null){
                size--;
                return newCurrent;
            }
            if (parent.left == current) parent.left = newCurrent;
            else parent.right = newCurrent;
        }
        else { //deleting the root with two children(left and right)
            Node successorParent = current; //searching in-order successor
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key; //copy the key and val
            current.val = successor.val;

            if (successorParent.left == successor) successorParent.left = successor.right;
            else successorParent.right = successor.right;
            //deleting the successor from its former place
            size--;
        }
        return root;


    }
    public static class Entry<K,V>{
        private final K key;
        private final V val;


        public Entry(K key, V val) {
            this.key=key;
            this.val=val;
        }
        //get methods for getting(returning) both key and val values during iteration
        public K getKey(){
            return key;
        }
        public V getVal(){
            return val;
        }
    }

    public Iterable<Entry<K, V>> iterator() {
        return new Iterable<>() {
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<>() {
                    private final Stack<Node> stack = new Stack<>(); //stack just for working methods such as pop
                    private Node current = root;

                    {
                        while (current != null) {
                            stack.push(current);
                            current = current.left;
                        }
                    }

                    public boolean hasNext() {
                        return !stack.isEmpty();
                    }

                    public Entry<K, V> next() {
                        Node node = stack.pop();
                        Entry<K, V> entry = new Entry<>(node.key, node.val);
                        Node temp = node.right;
                        while (temp != null) {
                            stack.push(temp);
                            temp = temp.left;
                        }
                        return entry;
                    }
                };
            }
        };
    }



}
