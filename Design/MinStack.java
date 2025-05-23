import java.util.Stack;

/**
 * Problem 155. Min Stack
 *
 * Design a stack that supports push, pop, top, and retrieving
 * the minimum element in constant time.
 *
 * Intuition:
 *   Alongside each value we push, we also record the minimum value
 *   of the stack at that point. That way, at any moment, the current
 *   minimum is right next to the top value.
 *
 * Approach:
 *   - Use a Stack of int arrays, where each entry is {value, currentMin}.
 *   - push(val): compare val to the current top’s currentMin (if any)
 *       to compute the new currentMin, then push {val, newMin}.
 *   - pop(): pop the top pair.
 *   - top(): return the top pair’s value.
 *   - getMin(): return the top pair’s currentMin.
 *
 * All operations are O(1) time and O(1) extra space per element.
 */
public class MinStack {
    private Stack<int[]> st;

    /** initialize your data structure here. */
    public MinStack() {
        st = new Stack<>();
    }

    /** push element val onto the stack. */
    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new int[]{val, val});
        } else {
            int currentMin = Math.min(st.peek()[1], val);
            st.push(new int[]{val, currentMin});
        }
    }

    /** remove the element on top of the stack. */
    public void pop() {
        st.pop();
    }

    /** get the top element. */
    public int top() {
        return st.peek()[0];
    }

    /** retrieve the minimum element in the stack. */
    public int getMin() {
        return st.peek()[1];
    }

    /** Simple test harness for the MinStack implementation. */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        System.out.println("Pushing -2");
        minStack.push(-2);
        System.out.println("Current top: " + minStack.top() + " | Current min: " + minStack.getMin());

        System.out.println("Pushing 0");
        minStack.push(0);
        System.out.println("Current top: " + minStack.top() + " | Current min: " + minStack.getMin());

        System.out.println("Pushing -3");
        minStack.push(-3);
        System.out.println("Current top: " + minStack.top() + " | Current min: " + minStack.getMin());

        System.out.println("getMin(): " + minStack.getMin() + " (expected -3)");

        System.out.println("pop()");
        minStack.pop();
        System.out.println("Current top: " + minStack.top() + " | Current min: " + minStack.getMin());

        System.out.println("getMin(): " + minStack.getMin() + " (expected -2)");

        // Additional tests
        System.out.println("\n-- Additional Tests --");
        MinStack ms = new MinStack();
        int[] values = {5, 3, 7, 3, 8, 2, 9};
        for (int v : values) {
            ms.push(v);
            System.out.printf("push(%d) -> min = %d%n", v, ms.getMin());
        }
        while (!ms.st.isEmpty()) {
            System.out.printf("top = %d, min = %d%n", ms.top(), ms.getMin());
            ms.pop();
        }
    }
}