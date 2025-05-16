/**
 * This is to set i-th bit of a number.
 * It uses bitwise operations to set the i-th bit of a number.
 * The algorithm works as follows:
 * 1. Create a mask by left-shifting 1 by i positions (1 << i).
 * 2. Perform a bitwise OR operation between the number and the mask.
 * 3. The result will be the number with the i-th bit set to 1.
 * This method is efficient and does not require any additional memory.
 * Note: This method only works for integer types and may not be suitable for floating-point numbers.
 * It is also important to note that this method may not be safe for all data types due to potential issues with signed integers.
 */
#include <iostream>
using namespace std;

bool set_ith_bit(int &num, int &i)
{
    num = num | (1<<i);
    return num;
}
int main()
{
    int num, i;
    cout << "Enter number : ";
    cin >> num;
    cout << "Enter the bit you want to set : ";
    cin >> i;
    cout << "The number after setting the " << i << "-th bit is : " << set_ith_bit(num, i) << endl;
}