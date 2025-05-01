package models;

public class MyHashTable {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K,V> next; //если будут повторы индексаци

        public HashNode(K key, V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString() {
            return "{"+ key+ " " + value+"}";
        }
    }
    public void HashNode<K,V>[] chainArray;
    private int M=11;
    private int size;

    public My
    public MyHashTable(int M){}




}
