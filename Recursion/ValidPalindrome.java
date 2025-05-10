/**
 *
 * Problem 125. Valid Palindrome
 *
 * Intuition:
 *   We need to compare characters from the ends toward the center, ignoring
 *   any non-alphanumeric characters and treating uppercase the same as lowercase.
 *   A two-pointer approach lets us skip unwanted chars in O(n) time.
 *
 * Approach:
 *   1. Convert the string to lowercase to handle case insensitivity.
 *   2. Use two indices, left = 0 and right = s.length()-1.
 *   3. While left < right:
 *        - Advance left until it points to an alphanumeric character (or left ≥ right).
 *        - Decrease right until it points to an alphanumeric character (or right ≤ left).
 *        - If left < right and s.charAt(left) ≠ s.charAt(right), return false.
 *        - Otherwise, left++ and right--.
 *   4. If all matching pairs are equal, return true.
 *
 * Time Complexity: O(n), where n is s.length(), since each character is examined at most once.
 * Space Complexity: O(1), only a few indices are used.
 */
public class ValidPalindrome {

    /**
     * Returns true if s is a palindrome after removing non-alphanumeric
     * characters and ignoring case; false otherwise.
     */
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {
            // Skip non-alphanumeric on left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric on right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Compare characters
            if (left < right && s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Helper to run a single test
    private static void runTest(String s, ValidPalindrome sol) {
        System.out.printf("Input: \"%s\"%n", s);
        System.out.println("Is palindrome? " + sol.isPalindrome(s));
        System.out.println();
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();

        String[] tests = {
            "A man, a plan, a canal: Panama",
            "race a car",
            " ",
            "0P",
            "No 'x' in Nixon",
            "Madam, I'm Adam"
        };

        for (String test : tests) {
            runTest(test, solution);
        }
    }
}
