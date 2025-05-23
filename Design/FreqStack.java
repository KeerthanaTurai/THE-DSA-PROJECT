import java.util.*;

/**
 * Problem 895. Maximum Frequency Stack
 *
 * Design a stack-like data structure that supports push and pop operations
 * where pop returns the most frequent element. If multiple elements are
 * tied for frequency, pop returns the one most recently pushed among them.
 *
 * Intuition:
 *   We need to track frequencies of each value and also retrieve the most
 *   recent value among those with maximum frequency. We can:
 *     1) Maintain a frequency map freqCounter: val → current frequency.
 *     2) Maintain a map of stacks freqGroup: frequency → stack of values
 *        that have that frequency, in push order.
 *     3) Track maxFreq, the current highest frequency present.
 *
 * Approach:
 *   - push(val):
 *       • Increment freqCounter[val].
 *       • Let f = freqCounter[val]. Update maxFreq = max(maxFreq, f).
 *       • Push val onto freqGroup[f].
 *   - pop():
 *       • Look at the stack freqGroup[maxFreq], pop its top value v.
 *       • Decrement freqCounter[v].
 *       • If freqGroup[maxFreq] becomes empty, decrement maxFreq.
 *       • Return v.
 *
 * Time Complexity: O(1) for both push and pop (amortized), since all operations
 *   on hash maps and stacks are constant time.
 * Space Complexity: O(N), where N is the number of pushes, for storing elements
 *   in the stacks and the frequency maps.
 */
public class FreqStack {
    // Map frequency → stack of values with that frequency
    private Map<Integer, Stack<Integer>> freqGroup;
    // Map value → its current frequency
    private Map<Integer, Integer> freqCounter;
    // Current maximum frequency
    private int maxFreq;

    public FreqStack() {
        freqGroup = new HashMap<>();
        freqCounter = new HashMap<>();
        maxFreq = 0;
    }
    
    /** Pushes an integer val onto the stack. */
    public void push(int val) {
        // 1) update the frequency of val
        int f = freqCounter.getOrDefault(val, 0) + 1;
        freqCounter.put(val, f);
        // 2) update maxFreq if necessary
        if (f > maxFreq) {
            maxFreq = f;
        }
        // 3) push val onto the stack corresponding to its new frequency
        freqGroup
            .computeIfAbsent(f, k -> new Stack<>())
            .push(val);
    }
    
    /**
     * Removes and returns the most frequent element.
     * If tie, returns the most recently pushed among those.
     */
    public int pop() {
        // 1) pop from the stack at maxFreq
        Stack<Integer> stack = freqGroup.get(maxFreq);
        int val = stack.pop();
        // 2) decrement its frequency
        freqCounter.put(val, maxFreq - 1);
        // 3) if no more values at this freq, reduce maxFreq
        if (stack.isEmpty()) {
            freqGroup.remove(maxFreq);
            maxFreq--;
        }
        return val;
    }

    /** Test harness for FreqStack */
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        // Sequence from example
        System.out.println("Pushing: 5,7,5,7,4,5");
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println("Pop → expected 5, got: " + freqStack.pop());
        System.out.println("Pop → expected 7, got: " + freqStack.pop());
        System.out.println("Pop → expected 5, got: " + freqStack.pop());
        System.out.println("Pop → expected 4, got: " + freqStack.pop());

        // Additional tests
        System.out.println("\nAdditional Tests:");
        FreqStack fs2 = new FreqStack();
        int[] pushes = {1,2,3,2,1,2,3,3};
        System.out.println("Pushing: " + Arrays.toString(pushes));
        for (int x : pushes) fs2.push(x);
        // Frequencies now: 1->2, 2->3, 3->3
        System.out.println("Pop → expected 3 (freq=3, most recent), got: " + fs2.pop());
        System.out.println("Pop → expected 2 (now both freq=3 and 2→2,3→2,1→2; 2 most recent), got: " + fs2.pop());
        System.out.println("Pop → expected 1 (freq=2, last remaining freq=2: values [1,3], 3 was popped, so 1 most recent), got: " + fs2.pop());
        System.out.println("Pop → expected 3 (freq=2), got: " + fs2.pop());
    }
}