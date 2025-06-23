// Leetcode 684. Redundant Connection
// https://leetcode.com/problems/redundant-connection/

public class Leetcode_684_RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // Initialize parent array for Union-Find
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // Function to find the root parent with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Function to union two sets, return false if already connected
        boolean union(int u, int v) {
            int pu = find(u);
            int pv = find(v);
            if (pu == pv) return false;  // Cycle found
            parent[pu] = pv;
            return true;
        }

        // Process all edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (!union(u, v)) {
                return edge; // This edge forms a cycle
            }
        }

        return new int[0]; // Should never reach here
    }

    // Helper methods must be moved outside main method if placed in full class
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private boolean union(int[] parent, int u, int v) {
        int pu = find(parent, u);
        int pv = find(parent, v);
        if (pu == pv) return false;
        parent[pu] = pv;
        return true;
    }
}

/*
Approach: Union-Find (Disjoint Set)
- Detect the edge that forms a cycle in an undirected graph.

Time Complexity: O(N * α(N)) — α(N) is the inverse Ackermann function (very slow-growing).
Space Complexity: O(N) — For the parent array.
*/
