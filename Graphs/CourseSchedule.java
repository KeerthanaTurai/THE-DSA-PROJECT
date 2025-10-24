/*
LeetCode 207. Course Schedule

Problem:
---------
There are numCourses courses labeled 0..numCourses-1. You’re given prerequisites where
prerequisites[i] = [a, b] means you must take course b before course a (edge: b -> a).
Return true if you can finish all courses (i.e., there is an ordering of all courses that
respects prerequisites); otherwise return false.

Examples:
---------
1) numCourses = 2, prerequisites = [[1,0]]  -> true
   Explanation: Take 0, then 1.

2) numCourses = 2, prerequisites = [[1,0],[0,1]] -> false
   Explanation: 0 -> 1 -> 0 forms a cycle, so impossible.

Constraints:
------------
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= a_i, b_i < numCourses
All prerequisite pairs are unique.

Intuition:
----------
This is a classic cycle-detection/topological-ordering problem on a directed graph.
If we can produce a topological ordering of all nodes (courses), then we can finish all courses.
If there is a cycle, topological ordering is impossible.

Approach (Kahn’s Algorithm - BFS on in-degrees):
-------------------------------------------------
1) Build a directed graph with adjacency list: b -> a for each [a, b].
2) Track in-degree for each course (number of prerequisites still required).
3) Initialize a queue with all courses that have in-degree 0 (ready to take now).
4) Repeatedly pop from the queue:
     - “Finish” that course (count++).
     - For each neighbor it unlocks, decrement its in-degree.
     - If any neighbor’s in-degree becomes 0, push it into the queue.
5) If we finish exactly numCourses courses, return true; otherwise false (cycle exists).

Time Complexity:
----------------
O(V + E) where V = numCourses and E = prerequisites.length
(We visit each node and edge a constant number of times.)

Space Complexity:
-----------------
O(V + E) for the adjacency list, in-degree array, and queue.
*/

import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build graph: b -> a (take b before a)
        for (int[] edge : prerequisites) {
            int a = edge[0], b = edge[1];
            inDegree[a]++;                // a needs one more prerequisite
            adjList.get(b).add(a);        // b unlocks a
        }

        // Start with all courses that have no prerequisites
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int finished = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            finished++;
            for (int next : adjList.get(course)) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return finished == numCourses;
    }

    // Simple local tests
    public static void main(String[] args) {
        System.out.println("Example 1: " + canFinish(2, new int[][]{{1,0}}));                 // true
        System.out.println("Example 2: " + canFinish(2, new int[][]{{1,0},{0,1}}));           // false

        // Edge & additional tests
        System.out.println("No prereqs: " + canFinish(5, new int[][]{}));                     // true
        System.out.println("Single course: " + canFinish(1, new int[][]{}));                  // true
        System.out.println("Chain: " + canFinish(4, new int[][]{{1,0},{2,1},{3,2}}));         // true
        System.out.println("3-cycle: " + canFinish(3, new int[][]{{1,0},{2,1},{0,2}}));       // false
        System.out.println("Disconnected w/ cycle: " + canFinish(4, new int[][]{{1,0},{3,2},{2,3}})); // false
        System.out.println("Multiple starts: " + canFinish(4, new int[][]{{2,0},{2,1},{3,2}})); // true
        System.out.println("Wide fan-in: " + canFinish(4, new int[][]{{3,0},{3,1},{3,2}}));   // true
        System.out.println("Out-of-order input: " + canFinish(3, new int[][]{{2,1},{1,0}}));  // true
    }
}
