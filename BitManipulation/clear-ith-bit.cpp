
/**
 * This program clears the ith bit of a given number.
 * It takes a number and the bit position (0-indexed) as input,
 * and outputs the number after clearing the specified bit.
 * The clearing is done using bitwise operations.
 * The ith bit is cleared by creating a mask with 1 at the ith position,
 * inverting it to get 0 at the ith position, and then using
 * the AND operation with the original number.
 * The result is the number with the ith bit cleared.
*/
#include <iostream>
using namespace std;

int clear_ith_bit(int num, int i) {
    // Create a mask with 1 at the ith position
    int mask = 1 << i;
    // Invert the mask to clear the ith bit
    mask = ~mask;
    // Clear the ith bit using AND operation
    return num & mask;
}
int main() {
    cout << "Enter a number: ";
    int num;
    cin >> num; 
    cout << "Enter the bit position to clear (0-indexed): ";
    int i;
    cin >> i;
    int result = clear_ith_bit(num, i);
    cout << "Number after clearing the " << i << "th bit: " << result << endl;
}