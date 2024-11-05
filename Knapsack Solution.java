import java.lang.*;
import java.util.Arrays;

public class knapsackSolution {

    public static void main(String[] args) {
        int a, i, k, n, b, Capacity, tempWeight, tempValue, bestValue, bestWeight;
        int remainder, nDigits;
        int Weights[] = {1, 4, 6, 2, 5, 10, 8, 3, 9, 1, 4, 2, 5, 8, 9, 1};
        int Values[] = {10, 5, 30, 8, 12, 30, 50, 10, 2, 10, 40, 80, 100, 25, 10, 5};
        int A[];

        A = new int[16];

        Capacity = 20; // Max pounds that can be carried
        n = 16; // number of items in the store
        b = 0;

        tempWeight = 0;
        tempValue = 0;
        bestWeight = 0;
        bestValue = 0;

        // Start iterating through all possible combinations using binary representation
        for (i = 0; i < 65536; i++) {
            remainder = i;

            // Initialize the binary representation array to all 0's
            for (a = 0; a < 16; a++) {
                A[a] = 0;
            }

            // Populate the binary representation array based on the current counter value
            nDigits = 16;
            for (a = 0; a < nDigits; a++) {
                A[a] = remainder % 2;  // Extract the last binary digit from the remainder
                remainder = remainder / 2;  // Move to the next binary digit
            }

            // Fill the knapsack based on the current binary representation
            for (k = 0; k < n; k++) {
                if (A[k] == 1) {
                    if (tempWeight + Weights[k] <= Capacity) {  // Check if adding the item exceeds the capacity
                        tempWeight = tempWeight + Weights[k];  // Add item weight to the temporary weight
                        tempValue = tempValue + Values[k];  // Add item value to the temporary value
                    }
                }
            }

            // Check if this knapsack has a higher value than the previous best
            if (tempValue > bestValue) {
                bestValue = tempValue;  // Save the new best value
                bestWeight = tempWeight;  // Save the corresponding best weight
                b++;  // Increment the number of valid knapsacks found so far
            }
            tempWeight = 0;  // Reset the temporary weight for the next iteration
            tempValue = 0;  // Reset the temporary value for the next iteration
        }

        // Print the best knapsack value and weight after the iteration
        System.out.printf("Weight: %d Value %d\n", bestWeight, bestValue);
        System.out.printf("Number of valid knapsack's: %d\n", b);

        // Call the dynamic programming solution with a copy of the original arrays
        int[] weightsCopy = Arrays.copyOf(Weights, Weights.length);
        int[] valuesCopy = Arrays.copyOf(Values, Values.length);
        dynamicProgrammingKnapsack(weightsCopy, valuesCopy, n, Capacity);
    }

    // Implementation of the dynamic programming solution for the knapsack problem
    public static void dynamicProgrammingKnapsack(int[] weights, int[] values, int n, int capacity) {
        int[][] dp = new int[n + 1][capacity + 1];  // Create a 2D array to store the optimal values

        // Fill the 2D array using dynamic programming approach
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    // If the current item can be included, find the maximum value between including it and not including it
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // If the current item cannot be included, inherit the value from the previous item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxValue = dp[n][capacity];  // The maximum value achievable in the knapsack
        int numValidKnapsacks = countValidKnapsacks(dp, capacity);  // Count the number of valid knapsacks

        // Print the results of the dynamic programming solution
        System.out.println("Weight: " + capacity);
        System.out.println("Value: " + dp[n][capacity]);
        System.out.println("Number of valid knapsack's: " + numValidKnapsacks);
    }

    // Helper method to count the number of valid knapsacks with the same value
    private static int countValidKnapsacks(int[][] dp, int W) {
        int count = 0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i][W] == dp[i - 1][W]) {
                count++;
            }
        }
        return count;
    }
}