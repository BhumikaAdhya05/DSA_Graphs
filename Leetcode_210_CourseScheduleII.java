// Leetcode 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/

import java.util.*;

public class Leetcode_210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Build graph and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            int course = pre[0], prereq = pre[1];
            graph.get(prereq).add(course);
            inDegree[course]++;
        }

        // Step 2: Add courses with no prerequisites to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        // Step 3: Perform topological sort and record order
        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[index++] = curr;

            for (int next : graph.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }

        return index == numCourses ? order : new int[0];  // Return empty if cycle
    }
}

/*
Approach: Topological Sort
- Same as Course Schedule I, but instead of checking feasibility,
  we return one valid course order.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/
