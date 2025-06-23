// Leetcode 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/

public class Leetcode_695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Start DFS if a land cell is found
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base case: out of bounds or water
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // Mark this cell as visited
        grid[r][c] = 0;
        int area = 1;

        // Explore in 4 directions
        area += dfs(grid, r + 1, c);
        area += dfs(grid, r - 1, c);
        area += dfs(grid, r, c + 1);
        area += dfs(grid, r, c - 1);

        return area;
    }
}

/*
Approach: Depth-First Search (DFS)
- Treat the grid as a graph and traverse each island using DFS.
- Count the area by recursively visiting all connected land cells (value 1).
- Mark visited cells to avoid cycles.

Time Complexity: O(m * n) — Every cell is visited once.
Space Complexity: O(m * n) — In the worst case, the recursion stack can be as deep as all land cells.
*/
