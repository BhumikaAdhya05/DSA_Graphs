// Leetcode 994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges/

import java.util.*;

public class Leetcode_994_RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();  // To perform BFS
        int freshOranges = 0;  // Count of fresh oranges

        // Step 1: Traverse the grid and collect initial rotten oranges in queue
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }

        // Edge case: No fresh oranges to rot
        if (freshOranges == 0) return 0;

        // Directions: up, down, left, right
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = 0;

        // Step 2: BFS to spread the rot each minute
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int row = pos[0], col = pos[1];

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // If new cell is valid and has a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;  // Rot it
                        queue.offer(new int[]{newRow, newCol});
                        freshOranges--;
                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) minutes++;  // Only increment minutes if rotting occurred
        }

        // If fresh oranges still remain, return -1
        return freshOranges == 0 ? minutes : -1;
    }
}

/*
Approach: Breadth-First Search (BFS)
- Use a queue to simulate the spread of rot level-by-level (minute-by-minute).
- Each rotten orange can rot its 4-directional neighbors.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/
