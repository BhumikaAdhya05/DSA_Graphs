// Leetcode 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/

import java.util.*;

public class Leetcode_743_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build graph using adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        // Min-heap to prioritize nodes with smaller accumulated delay
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, k}); // {time, node}

        // Map to track shortest time to reach each node
        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int time = current[0];
            int node = current[1];

            // Skip if already visited
            if (dist.containsKey(node)) continue;

            dist.put(node, time);

            // Traverse neighbors
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];
                    if (!dist.containsKey(nextNode)) {
                        heap.offer(new int[]{time + weight, nextNode});
                    }
                }
            }
        }

        // If not all nodes are reached, return -1
        if (dist.size() != n) return -1;

        // Return the maximum time to reach any node
        int maxTime = 0;
        for (int t : dist.values()) {
            maxTime = Math.max(maxTime, t);
        }

        return maxTime;
    }
}

/*
Time Complexity: O(E * log V) — Using Dijkstra's algorithm with a priority queue.
Space Complexity: O(V + E) — Graph, heap, and distance map.
*/
