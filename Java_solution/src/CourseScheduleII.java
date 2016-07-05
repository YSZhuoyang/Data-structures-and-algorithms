import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 7/5/16.
 *
 * Idea: find the order by traversing the graph using BFS.
 */
public class CourseScheduleII
{

	// My BFS solution which beats 76% java submissions
	// For detailed explanation, refer to Course Schedule solutions.
	public int[] findOrder(int numCourses, int[][] prerequisites)
	{
		if (numCourses <= 0)
		{
			return new int[0];
		}
		else if (prerequisites == null || prerequisites.length == 0)
		{
			int[] order = new int[numCourses];

			for (int i = 0; i < numCourses; i++)
			{
				order[i] = i;
			}

			return order;
		}

		// Only check connected edges
		List<Integer>[] successorLists = new ArrayList[numCourses];

		for (int i = 0; i < numCourses; i++)
		{
			successorLists[i] = new ArrayList<>();
		}

		int[] numPres = new int[numCourses];

		for (int[] pair : prerequisites)
		{
			successorLists[pair[1]].add(pair[0]);
			numPres[pair[0]]++;
		}

		ArrayDeque<Integer> q = new ArrayDeque<>();
		int[] order = new int[numCourses];
		int numReachable = 0;

		// Store root nodes into the queue
		for (int i = 0; i < numCourses; i++)
		{
			if (numPres[i] == 0)
			{
				q.add(i);
				order[numReachable++] = i;
			}
		}

		// Do a bfs
		while (!q.isEmpty())
		{
			int currNode = q.remove();

			for (int succ : successorLists[currNode])
			{
				numPres[succ]--;

				if (numPres[succ] == 0)
				{
					q.add(succ);
					order[numReachable++] = succ;
				}
			}
		}

		return (numReachable == numCourses) ? order : new int[0];
	}
}
