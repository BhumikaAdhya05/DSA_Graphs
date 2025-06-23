// Leetcode 133. Clone Graph
// https://leetcode.com/problems/clone-graph/

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}
    public Node(int val) { this.val = val; }
    public Node(int val, List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class Leetcode_133_CloneGraph {

    // HashMap to store original node -> cloned node mapping
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If node already cloned, return it
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Step 1: Clone the node (without neighbors)
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // Step 2: Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}

/*
Approach: Depth-First Search (DFS) + HashMap
- Use a HashMap to avoid revisiting the same node.
- For each node, create a clone, then recursively clone its neighbors.

Time Complexity: O(N), where N is the number of nodes.
Space Complexity: O(N) for recursion stack and visited map.
*/
