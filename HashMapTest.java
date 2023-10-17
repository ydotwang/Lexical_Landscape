import java.util.ArrayList;
import java.util.Collections;

/**
 * Yuyang Wang
 * HashMapTest
 * HashMap test class
 * This program tests the HashMap class
 * Date: 4/16/2023
 * To compile: javac HashMapTest.java
 * To run: java -ea HashMapTest
 */

public class HashMapTest {
    public static void main(String[] args) {
        HashMapTest test = new HashMapTest();
        test.testPut();
        test.testGet();
        test.testRemove();
        test.testSize();
        test.testClear();
        test.testContainsKey();
        test.testKeySet();
        test.testValues();

        System.out.println("All tests passed!");
    }

    public void testPut() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int expectedSize = 0;
        hashMap.put("one", 1);
        expectedSize++;
        hashMap.put("two", 2);
        expectedSize++;
        hashMap.put("three", 3);
        expectedSize++;

        assert hashMap.size() == expectedSize : "testPut failed - incorrect size";
    }

    public void testGet() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assert hashMap.get("one") == 1 : "testGet failed - incorrect value for key 'one'";
        assert hashMap.get("two") == 2 : "testGet failed - incorrect value for key 'two'";
        assert hashMap.get("three") == 3 : "testGet failed - incorrect value for key 'three'";
    }

    public void testRemove() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        hashMap.remove("two");

        assert hashMap.get("two") == null : "testRemove failed - key 'two' still present";
        assert hashMap.size() == 2 : "testRemove failed - incorrect size";
    }

    public void testSize() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assert hashMap.size() == 3 : "testSize failed - incorrect size";
    }

    public void testClear() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        hashMap.clear();

        assert hashMap.size() == 0 : "testClear failed - incorrect size";
    }

    public void testContainsKey() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        assert hashMap.containsKey("one") : "testContainsKey failed - key 'one' not found";
        assert hashMap.containsKey("two") : "testContainsKey failed - key 'two' not found";
        assert hashMap.containsKey("three") : "testContainsKey failed - key 'three' not found";
        assert !hashMap.containsKey("four") : "testContainsKey failed - key 'four' should not be found";
    }

    public void testKeySet() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three");

        ArrayList<String> actual = hashMap.keySet();
        Collections.sort(expected);
        Collections.sort(actual);

        assert expected.equals(actual) : "testKeySet failed";
    }

    public void testValues() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        ArrayList<Integer> actual = hashMap.values();
        Collections.sort(expected);
        Collections.sort(actual);

        assert expected.equals(actual) : "testValues failed";
    }

}
