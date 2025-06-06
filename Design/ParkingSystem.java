/**
 * Problem 1603. Design Parking System
 *
 * Intuition:
 *   We need to track the remaining slots for three car types (big, medium, small).
 *   On each addCar call, check if there is at least one slot of the requested type;
 *   if so, decrement that count and return true; otherwise return false.
 *
 * Approach:
 *   - Maintain an array parkingSpace of length 3, where:
 *       index 0 = number of big slots,
 *       index 1 = number of medium slots,
 *       index 2 = number of small slots.
 *   - In the constructor, initialize these counts from the inputs.
 *   - For addCar(carType):
 *       • Translate carType (1, 2, 3) to array index (0, 1, 2).
 *       • If parkingSpace[index] > 0, decrement and return true.
 *       • Otherwise, return false.
 *
 * Time Complexity:
 *   O(1) per addCar call.
 *
 * Space Complexity:
 *   O(1) total extra space (just three counters).
 */
public class ParkingSystem {
    private final int[] parkingSpace;

    /** Initialize with the number of slots for big, medium, and small. */
    public ParkingSystem(int big, int medium, int small) {
        parkingSpace = new int[3];
        parkingSpace[0] = big;
        parkingSpace[1] = medium;
        parkingSpace[2] = small;
    }

    /**
     * Attempts to park a car of the given type.
     * @param carType 1 = big, 2 = medium, 3 = small
     * @return true if a slot was available (and is now occupied), false otherwise
     */
    public boolean addCar(int carType) {
        int idx = carType - 1; 
        if (parkingSpace[idx] > 0) {
            parkingSpace[idx]--;
            return true;
        }
        return false;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        // Example 1
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println(parkingSystem.addCar(1)); // true (big slot available)
        System.out.println(parkingSystem.addCar(2)); // true (medium slot available)
        System.out.println(parkingSystem.addCar(3)); // false (no small slots)
        System.out.println(parkingSystem.addCar(1)); // false (big slot already used)

        // Additional tests
        System.out.println("\nAdditional Tests:");

        ParkingSystem ps2 = new ParkingSystem(2, 0, 1);
        System.out.println(ps2.addCar(1)); // true (big: now 1 left)
        System.out.println(ps2.addCar(1)); // true (big: now 0 left)
        System.out.println(ps2.addCar(1)); // false (no big slots)
        System.out.println(ps2.addCar(3)); // true (small: now 0 left)
        System.out.println(ps2.addCar(3)); // false (no small slots)
        System.out.println(ps2.addCar(2)); // false (no medium slots)
    }
}