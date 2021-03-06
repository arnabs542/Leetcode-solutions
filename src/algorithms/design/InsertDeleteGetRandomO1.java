package algorithms.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qihao He
 * @date 09/29/2019
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * hashtable, doubly linked list
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
public class InsertDeleteGetRandomO1 {
    List<Integer> nums;
    /**
     * K: number, V: index
     * */
    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        nums = new ArrayList<>();
        map = new HashMap<>();
    }

    /** Inserts a value to the set.
     * Returns true if the set did not
     * already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set.
     * Returns true if the set contained
     * the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        if (index < nums.size() - 1) {
            // not the last one,
            // then swap the last one with this val
            int tailVal = nums.get(nums.size() - 1);
            nums.set(index, tailVal);
            map.put(tailVal, index);
        }
        map.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get((int)(Math.random() * nums.size()));
    }
}
