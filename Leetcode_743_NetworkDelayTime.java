// Leetcode 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/

import java.util.*;

public class Leetcode_743_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the adjacency list: node -> list of [neighbor, weight]
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // Min-heap to get node with shortest delay time
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, k}); // {time, node}

        // Distance map to track shortest time to reach each node
        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int time = curr[0];
            int node = curr[1];

            // Skip if already visited
            if (dist.containsKey(node)) continue;

            dist.put(node, time);  // Record shortest time

            // Explore neighbors
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int edgeWeight = neighbor[1];

                    if (!dist.containsKey(nextNode)) {
                        heap.offer(new int[]{time + edgeWeight, nextNode});
                    }
                }
            }
        }

        // If not all nodes are reached, return -1
        if (dist.size() != n) return -1;

        // Return the maximum delay time
        int maxTime = 0;
        for (int delay : dist.values()) {
            maxTime = Math.max(maxTime, delay);
        }

        return maxTime;
    }
}

/*
Approach: Dijkstra's Algorithm (Greedy + Min Heap)
- Use a priority queue to always expand the node with the smallest known delay.
- Track the shortest delay to each node using a distance map.
- If a node is reached again, skip it (already visited with a shorter path).

Time Complexity: O(E * log V) — Each edge may go through a heap operation.
Space Complexity: O(V + E) — Graph representation, distance map, and heap.
*/
