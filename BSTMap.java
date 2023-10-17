
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * BSTMap
 * This program implements a map using a BST
 * date 4/17/2023
 * To compile: javac BSTMap.java
 * To run: java BSTMap
 */

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class implements a map using a BST
 * 
 * @param <K> the key
 * @param <V> the value
 * @author Yuyang Wang
 * @version 1.0
 */
public class BSTMap<K, V> implements MapSet<K, V> {

    private static class Node<K, V> extends KeyValuePair<K, V> {
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;

        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
            height = 1;

        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            super(key, value);
            this.left = left;
            this.right = right;
            height = 1;

        }
    }

    private int size;
    private Node<K, V> root;
    private Comparator<K> comparator;

    public BSTMap(Comparator<K> comparator) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<K>() {
                @SuppressWarnings("unchecked")
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }
            };
        }
        root = null;
        size = 0;
    }

    public BSTMap() {
        this(null);
    }

    @Override
    public V put(K key, V value) {
        if (size == 0) {
            root = new Node<K, V>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }

    }

    private V put(K key, V value, Node<K, V> curNode) {
        if (comparator.compare(key, curNode.getKey()) < 0) {
            if (curNode.left == null) {
                curNode.left = new Node<K, V>(key, value);
                size++;
            } else {
                return put(key, value, curNode.left);
            }
        } else if (comparator.compare(key, curNode.getKey()) > 0) {
            if (curNode.right == null) {
                curNode.right = new Node<K, V>(key, value);
                size++;
            } else {
                return put(key, value, curNode.right);
            }
        } else {
            V oldValue = curNode.getValue();
            curNode.setValue(value);
            return oldValue;
        }

        updateHeight(curNode);
        return null;
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);

    }

    private boolean containsKey(K key, Node<K, V> cur) {
        if (cur == null) {
            return false;
        }
        int cmp = comparator.compare(key, cur.getKey());
        if (cmp < 0) {
            return containsKey(key, cur.left);
        } else if (cmp > 0) {
            return containsKey(key, cur.right);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int cmp = comparator.compare(key, cur.getKey());
        if (cmp < 0) {
            return get(key, cur.left);
        } else if (cmp > 0) {
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }

    @Override
    public V remove(K key) {
        Node<K, V> parent = null;
        Node<K, V> current = root;

        while (current != null) {
            @SuppressWarnings("unchecked")
            int compare = ((Comparable<K>) key).compareTo(current.getKey());

            if (compare < 0) {
                parent = current;
                current = current.left;
            } else if (compare > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) {
            return null; // Key not found
        }

        V removedValue = current.getValue();
        handleReplacement(current, parent);
        this.size -= 1;
        return removedValue;
    }

    private void handleReplacement(Node<K, V> toDelete, Node<K, V> toDeleteParent) {
        Node<K, V> replacement;

        if (toDelete.left == null) {
            replacement = toDelete.right;
        } else if (toDelete.right == null) {
            replacement = toDelete.left;
        } else {
            // Find the next largest node (in-order successor)
            Node<K, V> parentOfReplacement = toDelete;
            replacement = toDelete.right;
            while (replacement.left != null) {
                parentOfReplacement = replacement;
                replacement = replacement.left;
            }

            // Remove the in-order successor from its position
            if (parentOfReplacement != toDelete) {
                parentOfReplacement.left = replacement.right;
                replacement.right = toDelete.right;
            }

            // Update the left child of the replacement
            replacement.left = toDelete.left;
        }

        // Update the parent's child reference
        if (toDeleteParent == null) {
            root = replacement;
        } else if (toDeleteParent.left == toDelete) {
            toDeleteParent.left = replacement;
        } else {
            toDeleteParent.right = replacement;
        }
        updateHeight(toDeleteParent);
    }

    @Override
    public ArrayList<K> keySet() {
        return keySet(root, new ArrayList<K>());
    }

    private ArrayList<K> keySet(Node<K, V> cur, ArrayList<K> keys) {
        if (cur == null) {
            return keys;
        }
        keySet(cur.left, keys);
        keys.add(cur.getKey());
        keySet(cur.right, keys);
        return keys;

    }

    @Override
    public ArrayList<V> values() {
        return values(root, new ArrayList<V>());
    }

    private ArrayList<V> values(Node<K, V> cur, ArrayList<V> values) {
        if (cur == null) {
            return values;
        }
        values(cur.left, values);
        values.add(cur.getValue());
        values(cur.right, values);
        return values;
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        return entrySet(root, new ArrayList<KeyValuePair<K, V>>());
    }

    private ArrayList<KeyValuePair<K, V>> entrySet(Node<K, V> cur, ArrayList<KeyValuePair<K, V>> entries) {
        if (cur == null) {
            return entries;
        }
        entrySet(cur.left, entries);
        entries.add(new KeyValuePair<>(cur.getKey(), cur.getValue()));
        entrySet(cur.right, entries);
        return entries;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        // resert fields to initial values
        root = null;
        size = 0;

    }

    @Override
    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(BSTMap.Node<K, V> cur) {
        if (cur == null) {
            return 0;
        }
        int leftDepth = maxDepth(cur.left);
        int rightDepth = maxDepth(cur.right);
        return Math.max(leftDepth, rightDepth) + 1;

    }

    public String toString() {
        return toString(root, "", "root");
    }

    private String toString(Node<K, V> cur, String prefix, String position) {
        if (cur == null) {
            return "";
        }

        String currentNode = String.format("%s %s: < %s -> %s > %n", prefix, position, cur.getKey(), cur.getValue());
        String leftSubtree = toString(cur.left, prefix + "  ", "left");
        String rightSubtree = toString(cur.right, prefix + "  ", "right");

        return rightSubtree + currentNode + leftSubtree;
    }

    private int getHeight(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(Node<K, V> node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    public static void main(String[] args) {
        // this will sort the strings lexicographically (dictionary-order)
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        System.out.println(words);

        // this will sort the strings in reverse lexicographic order
        BSTMap<String, Integer> wordsReverse = new BSTMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }

        });
        wordsReverse.put("ten", 10);
        wordsReverse.put("five", 5);
        wordsReverse.put("three", 3);

        System.out.println(wordsReverse);
    }

    private int getNumberOfUnbalancedNodes(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        int leftCount = getNumberOfUnbalancedNodes(node.left);
        int rightCount = getNumberOfUnbalancedNodes(node.right);

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return leftCount + rightCount + 1;
        } else {
            return leftCount + rightCount;
        }
    }

    @Override
    public int getNumberOfImbalancedNodes() {
        return getNumberOfUnbalancedNodes(root);
    }

}
