import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 7/3/16.
 *
 * Two approaches (both with a time complexity of O(V + E)):
 * 1. Detect whether a graph contains a cycle using DFS
 * 2. Find out whether all nodes are reachable using BFS
 *
 * Online course of graph searching: https://www.youtube.com/watch?v=AfSk24UTFS8
 */
public class CourseSchedule
{

	// My DFS solution which beats 91% java submissions
	// Faster than BFS. DFS is more suitable for detecting cycles
	// within a graph.
	//
	// A good tutorial of detecting cycle in a graph using DFS:
	// https://www.youtube.com/watch?v=rKQaZuoUR4M
	public boolean canFinish(int numCourses, int[][] prerequisites)
	{
		if (prerequisites == null || numCourses < 0)
		{
			return false;
		}
		else if (prerequisites.length == 0 || numCourses == 0)
		{
			return true;
		}

		// Only check edges connected of each node during DFS instead of
		// trying all edges
		List<Integer>[] prerequisiteLists = new ArrayList[numCourses];

		for (int i = 0; i < numCourses; i++)
		{
		    prerequisiteLists[i] = new ArrayList<>();
		}

		for (int[] pair : prerequisites)
		{
		    prerequisiteLists[pair[0]].add(pair[1]);
		}

		// Initialize a visited set and a visiting set
		boolean[] visiting = new boolean[numCourses];
		boolean[] visited = new boolean[numCourses];

		// Try a DFS from every node
		for (int i = 0; i < numCourses; i++)
		{
			if (!dfs(visiting, visited, prerequisiteLists, i))
			{
				return false;
			}
		}

		return true;
	}

	private boolean dfs(boolean[] vSet, boolean[] fSet, List<Integer>[] prerequisiteLists, int currNode)
	{
		// Current node is not in the visited set
		if (!fSet[currNode])
		{
			// A cycle is detected if the current node being visited
			// is already in the visiting set.
			if (vSet[currNode])
			{
				return false;
			}
			// Add the current node into the visiting set and keep going
			// through its children
			else
			{
				vSet[currNode] = true;

				for (int i : prerequisiteLists[currNode])
				{
					if (!dfs(vSet, fSet, prerequisiteLists, i))
					{
						return false;
					}
				}

				fSet[currNode] = true;
				vSet[currNode] = false;

				return true;
			}
		}
		// Current node is already in the finished set, which means the
		// whole subtree starting from that node has been entirely visited
		else
		{
			return true;
		}
	}

	// My BFS solution which beats 80% java submissions.
	// BFS is more suitable for finding a shortest path between
	// the root node and another node within a graph.
	/*public boolean canFinish(int numCourses, int[][] prerequisites)
	{
		if (prerequisites == null || numCourses < 0)
		{
			return false;
		}
		else if (prerequisites.length == 0 || numCourses == 0)
		{
			return true;
		}

		// Only check edges connected during DFS instead of
		// trying all edges
		List<Integer>[] successorLists = new ArrayList[numCourses];
		// Count number of prerequisites for each course
		int[] numPres = new int[numCourses];

		for (int i = 0; i < numCourses; i++)
		{
		    successorLists[i] = new ArrayList<>();
		}

		for (int[] pair : prerequisites)
		{
		    successorLists[pair[1]].add(pair[0]);
		    numPres[pair[0]]++;
		}

		// Store nodes with no prerequisite into the queue
		// as start nodes
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int numReachable = 0;

		for (int i = 0; i < numCourses; i++)
		{
			if (numPres[i] == 0)
			{
				q.add(i);
				numReachable++;
			}
		}

		// Do a bfs
		while (!q.isEmpty())
		{
			int currNode = q.remove();

			for (int i : successorLists[currNode])
			{
			    numPres[i]--;

				if (numPres[i] == 0)
				{
					q.add(i);
					numReachable++;
				}
			}
		}

		return numReachable == numCourses;
	}*/
}
