
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * AVLTreeMap
 * This program implements a map using an AVL tree
 * date 4/17/2023
 * To compile: javac AVLTreeMap.java
 * To run: java AVLTreeMap
 */

import java.util.ArrayList;
import java.util.Comparator;

/**
 * AVLTreeMap
 * This class implements a map using an AVL tree
 * 
 * @param <K> the key type
 * @param <V> the value type
 * @author Yuyang Wang
 * @version 1.0
 */
public class AVLTreeMap<K, V> implements MapSet<K, V> {

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
    }

    private int size;
    private Node<K, V> root;
    private Comparator<K> comparator;

    Comparator<String> caseAndLexicographicOrder = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int caseInsensitiveComparison = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
            if (caseInsensitiveComparison != 0) {
                return caseInsensitiveComparison;
            }
            return o1.compareTo(o2);
        }
    };

    public AVLTreeMap(Comparator<K> comparator) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = (Comparator<K>) caseAndLexicographicOrder;
        }
        root = null;
        size = 0;
    }

    public AVLTreeMap() {
        this(null);
    }

    @Override
    public V put(K key, V value) {
        if (size == 0) {
            root = new Node<K, V>(key, value);
            size++;
            return null;
        } else {
            OldValueHolder<V> oldValueHolder = new OldValueHolder<>(null);
            root = put(key, value, root, oldValueHolder);
            return oldValueHolder.getValue();
        }
    }

    private Node<K, V> put(K key, V value, Node<K, V> node, OldValueHolder<V> oldValueHolder) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        int cmp = comparator.compare(key, node.getKey());
        if (cmp < 0) {
            node.left = put(key, value, node.left, oldValueHolder);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right, oldValueHolder);
        } else {
            V oldValue = node.getValue();
            oldValueHolder.setValue(oldValue);
            node.setValue(value);
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private static class OldValueHolder<V> {
        private V value;

        public OldValueHolder(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getHeight(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(Node<K, V> node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node<K, V> leftRotate(Node<K, V> x) {
        Node<K, V> y = x.right;
        x.right = y.left;
        y.left = x;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private Node<K, V> rightRotate(Node<K, V> y) {
        Node<K, V> x = y.left;
        y.left = x.right;
        x.right = y;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
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
        // TODO: Implement remove method for AVLTreeMap
        throw new UnsupportedOperationException("remove method is not implemented for AVLTreeMap");
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
        root = null;
        size = 0;
    }

    @Override
    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node<K, V> cur) {
        if (cur == null) {
            return 0;
        }
        int leftDepth = maxDepth(cur.left);
        int rightDepth = maxDepth(cur.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        printTree(root, "", "ROOT", sb);
        return sb.toString();
    }

    private void printTree(Node<K, V> node, String prefix, String position, StringBuilder sb) {
        if (node != null) {
            printTree(node.right, prefix + "  ", "RIGHT", sb);
            sb.append(prefix)
                    .append(position)
                    .append(": (height: ")
                    .append(node.height)
                    .append(") ")
                    .append("<")
                    .append(node.getKey())
                    .append(", ")
                    .append(node.getValue())
                    .append(">")
                    .append("\n");
            printTree(node.left, prefix + "  ", "LEFT", sb);
        }
    }

    public int getNumberOfUnbalancedNodes() {
        return getNumberOfUnbalancedNodes(root);
    }

    private int getNumberOfUnbalancedNodes(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        int leftCount = getNumberOfUnbalancedNodes(node.left);
        int rightCount = getNumberOfUnbalancedNodes(node.right);

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 || balanceFactor < -1) {
            return leftCount + rightCount + 1;
        } else {
            return leftCount + rightCount;
        }
    }

    // Add this method in the AVLTreeMap class
    public int getNumberOfImbalancedNodes() {
        return getNumberOfImbalancedNodes(root);
    }

    private int getNumberOfImbalancedNodes(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        int leftCount = getNumberOfImbalancedNodes(node.left);
        int rightCount = getNumberOfImbalancedNodes(node.right);

        int balanceFactor = Math.abs(getBalanceFactor(node));
        if (balanceFactor > 1) {
            return leftCount + rightCount + 1;
        } else {
            return leftCount + rightCount;
        }
    }

    public static void main(String[] args) {
        Comparator<String> caseAndLexicographicOrder = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int caseInsensitiveComparison = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
                if (caseInsensitiveComparison != 0) {
                    return caseInsensitiveComparison;
                }
                return o1.compareTo(o2);
            }
        };

        AVLTreeMap<String, Integer> words = new AVLTreeMap<>(caseAndLexicographicOrder);
        words.put("ten", 10);
        words.put("three", 3);
        words.put("five", 5);
        words.put("two", 2);
        words.put("one", 1);
        System.out.println(words);
    }

}
