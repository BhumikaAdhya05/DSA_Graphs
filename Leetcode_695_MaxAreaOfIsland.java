// Leetcode 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/

public class Leetcode_695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If it's land (1), perform DFS to calculate area
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base case: if out of bounds or water (0), return 0
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // Mark current land cell as visited
        grid[r][c] = 0;
        int area = 1;

        // Explore 4 directions
        area += dfs(grid, r + 1, c);
        area += dfs(grid, r - 1, c);
        area += dfs(grid, r, c + 1);
        area += dfs(grid, r, c - 1);

        return area;
    }
}

/*
Time Complexity: O(m * n) — Each cell is visited once.
Space Complexity: O(m * n) — In worst case, recursion stack uses space equal to number of land cells.
*/
