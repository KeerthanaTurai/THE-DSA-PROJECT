/**
 * This program swaps two numbers without using a third variable.
 * It uses bitwise XOR operation to achieve this.
 * The XOR operation has the property that a ^ a = 0 and a ^ 0 = a.
 * By using this property, we can swap two numbers without using a temporary variable.
 * The algorithm works as follows:
 * 1. a = a ^ b
 * 2. b = a ^ b (now b is the original value of a)
 * 3. a = a ^ b (now a is the original value of b)
 * This method is efficient and does not require any additional memory.
 * Note: This method only works for integer types and may not be suitable for floating-point numbers.
 * It is also important to note that this method may not be safe for all data types due to potential issues with signed integers.
 */
#include <iostream>
using namespace std;

void swapNumbers(int &a, int &b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
}

int main() {
    int a = 10, b = 20;
    cout << "a = " << a << " b = " << b << endl;
    swapNumbers(a, b);
    cout << "a = " << a << " b = " << b << endl;
    return 0;
}