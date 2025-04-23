import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem: Print all divisors of a positive integer n in ascending order.
 *
 * Intuition:
 *   Every divisor d of n pairs with n/d. By iterating only up to √n, we can
 *   discover both members of each pair in a single pass.
 *
 * Approach:
 *   1. Handle the trivial case n = 1 by adding 1.
 *   2. Initialize a list of factors and add 1 (and n if n ≠ 1).
 *   3. For each i from 2 to floor(√n):
 *        - If i divides n, add i.
 *        - If i and n/i are distinct, add n/i as well.
 *   4. Sort the list of collected divisors.
 *   5. Print them in order.
 *
 * Time Complexity:
 *   O(√n) to scan for divisors plus O(d log d) to sort, where d is the number
 *   of divisors. In the worst case d = O(n^ε), but for typical n this is small.
 *
 * Space Complexity:
 *   O(d) additional space to store the divisors.
 */
public class DivisorsPrinter {

    /**
     * Prints all divisors of n in ascending order, separated by spaces.
     *
     * @param n the positive integer whose divisors to print
     */
    public static void print_divisors(int n) {
        List<Integer> factors = new ArrayList<>();
        // 1 is always a divisor
        factors.add(1);
        // n itself, unless n is 1 (already added)
        if (n != 1) {
            factors.add(n);
        }

        // Find divisor pairs up to sqrt(n)
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                int paired = n / i;
                if (paired != i) {
                    factors.add(paired);
                }
            }
        }

        // Sort and print
        Collections.sort(factors);
        for (int f : factors) {
            System.out.print(f + " ");
        }
    }

    public static void main(String[] args) {
        int[] testValues = {
            1,    // divisors: [1]
            16,   // divisors: [1, 2, 4, 8, 16]
            17,   // divisors: [1, 17] (prime)
            28,   // divisors: [1, 2, 4, 7, 14, 28]
            100   // divisors: [1, 2, 4, 5, 10, 20, 25, 50, 100]
        };

        for (int n : testValues) {
            System.out.printf("Divisors of %d: ", n);
            print_divisors(n);
            System.out.println();
        }
    }
}
