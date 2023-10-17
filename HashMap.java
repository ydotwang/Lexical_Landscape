
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * HashMap
 * This program implements a map using an ArrayList
 * date 4/17/2023
 * To compile: javac HashMap.java
 * To run: java HashMap
 */

import java.util.ArrayList;

/**
 * This class implements a map using a HashMap
 * 
 * @param <K> the key
 * @param <V> the value
 * @author Yuyang Wang
 * @version 1.0
 */
public class HashMap<K, V> implements MapSet<K, V> {

    public static class Node<K, V> extends KeyValuePair<K, V> {
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            super(key, value);
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private double maxLoadFactor;

    public HashMap(int initialCapacity, double maxLoadFactor) {

        buckets = (Node<K, V>[]) new Node[initialCapacity];
        size = 0;
        this.maxLoadFactor = maxLoadFactor;
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    public HashMap() {
        this(16);
    }

    private int capacity() {
        return buckets.length;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity();
    }

    @Override
    public V put(K key, V value) {
        if (value == null) {
            return null;
        }

        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Node<K, V>(key, value, null);
            size++;

        } else {
            for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
                if (curNode.getKey().equals(key)) {
                    V oldValue = curNode.getValue();
                    curNode.setValue(value);
                    return oldValue;
                }
            }
            buckets[index] = new Node<K, V>(key, value, buckets[index]);
            size++;
        }

        if (size > maxLoadFactor * capacity()) {
            rehash(capacity() * 2);
        }
        return null;
    }

    private void rehash(int newCapacity) {
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newCapacity];
        for (int i = 0; i < capacity(); i++) {
            for (Node<K, V> curNode = buckets[i]; curNode != null; curNode = curNode.next) {
                int index = Math.abs(curNode.getKey().hashCode()) % newCapacity;
                newBuckets[index] = new Node<K, V>(curNode.getKey(), curNode.getValue(), newBuckets[index]);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return false;
        }
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        for (Node<K, V> curNode = buckets[hash(key)]; curNode != null; curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                return curNode.getValue();
            }
        }
        return null;

    }

    @Override
    public V remove(K key) {

        int index = hash(key);
        Node<K, V> prevNode = null;
        for (Node<K, V> curNode = buckets[index]; curNode != null; prevNode = curNode, curNode = curNode.next) {
            if (curNode.getKey().equals(key)) {
                if (prevNode == null) {
                    buckets[index] = curNode.next;
                } else {
                    prevNode.next = curNode.next;
                }
                size--;
                if (size < maxLoadFactor * capacity() / 4) {
                    rehash(capacity() / 2);
                }
                return curNode.getValue();
            }
        }
        return null;

    }

    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.next) {
                keys.add(curNode.getKey());
            }
        }
        return keys;
    }

    @Override
    public ArrayList<V> values() {
        ArrayList<V> vals = new ArrayList<>();
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.next) {
                vals.add(curNode.getValue());
            }
        }
        return vals;
    }

    @Override
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<MapSet.KeyValuePair<K, V>> entries = new ArrayList<>();
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.next) {
                entries.add(curNode);
            }
        }
        return entries;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = (Node<K, V>[]) new Node[capacity()];
        size = 0;
    }

    @Override
    public int maxDepth() {
        int maxDepth = 0;
        for (Node<K, V> bucket : buckets) {
            int depth = 0;
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.next) {
                depth++;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }
        return maxDepth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.next) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(curNode.getKey()).append("=").append(curNode.getValue());
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int getNumberOfImbalancedNodes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfImbalancedNodes'");
    }
}
