/**
 * Problem 232. Implement Queue using Stacks
 *
 * Intuition:
 *   We maintain two stacks, stack1 for incoming elements and stack2 for outgoing.
 *   New elements are always pushed onto stack1. When we need to pop or peek the
 *   front element, we transfer everything from stack1 to stack2 (if stack2 is empty),
 *   reversing the order so that the oldest element is on top of stack2.
 *
 * Approach:
 *   - push(x): push x onto stack1.
 *   - pop(): if stack2 is empty, move all elements from stack1 to stack2; then pop from stack2.
 *   - peek(): if stack2 is empty, move all elements from stack1 to stack2; then peek stack2.
 *   - empty(): return true if both stack1 and stack2 are empty.
 *
 * Time Complexity:
 *   Amortized O(1) per operation. Each element is moved at most once from stack1 to stack2.
 *   Worst-case O(n) for a single pop/peek when stack2 is empty and all elements transfer.
 *
 * Space Complexity:
 *   O(n) total for the two stacks, where n is the number of elements in the queue.
 */
import java.util.Stack;

public class QueueUsingStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public QueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns true if the queue is empty, false otherwise. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // Simple test
    public static void main(String[] args) {
        QueueUsingStacks myQueue = new QueueUsingStacks();
        myQueue.push(1);               // queue is [1]
        myQueue.push(2);               // queue is [1,2]
        System.out.println(myQueue.peek());  // returns 1
        System.out.println(myQueue.pop());   // returns 1, queue is [2]
        System.out.println(myQueue.empty()); // returns false
    }
}
