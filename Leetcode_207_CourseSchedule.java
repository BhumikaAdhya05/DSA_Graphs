// Leetcode 207. Course Schedule
// https://leetcode.com/problems/course-schedule/

import java.util.*;

public class Leetcode_207_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the adjacency list and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0], prereq = pre[1];
            graph.get(prereq).add(course);
            inDegree[course]++;
        }

        // Step 2: Add all courses with 0 in-degree to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int finished = 0;

        // Step 3: BFS (Topological Sort)
        while (!queue.isEmpty()) {
            int course = queue.poll();
            finished++;

            for (int next : graph.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }

        return finished == numCourses;
    }
}

/*
Approach: Topological Sort (Kahn's Algorithm)
- Track prerequisites using in-degree array.
- Perform BFS to process courses with 0 in-degree.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/
