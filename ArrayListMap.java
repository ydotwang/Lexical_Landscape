
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * ArrayListMap
 * This program implements a map using an ArrayList
 * date 4/17/2023
 * To compile: javac ArrayListMap.java
 * To run: java ArrayListMap
 */

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListMap<K, V> implements MapSet<K, V> {

    private ArrayList<KeyValuePair<K, V>> map;
    private Comparator<K> comparator;

    public ArrayListMap(Comparator<K> comparator) {
        this.map = new ArrayList<>();
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<K>() {
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }
            };
        }
    }

    public ArrayListMap() {
        this(null);
    }

    @Override
    public V put(K key, V value) {
        for (KeyValuePair<K, V> entry : map) {
            if (comparator.compare(entry.getKey(), key) == 0) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        map.add(new KeyValuePair<>(key, value));
        return null;
    }

    @Override
    public V get(K key) {
        for (KeyValuePair<K, V> entry : map) {
            if (comparator.compare(entry.getKey(), key) == 0) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (KeyValuePair<K, V> entry : map) {
            if (comparator.compare(entry.getKey(), key) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public ArrayList<K> keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public ArrayList<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public int maxDepth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxDepth'");
    }

    @Override
    public int getNumberOfImbalancedNodes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfImbalancedNodes'");
    }

}
