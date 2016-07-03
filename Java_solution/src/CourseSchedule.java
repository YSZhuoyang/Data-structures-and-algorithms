import java.util.ArrayDeque;

/**
 * Created by oscar on 7/3/16.
 *
 * Idea: detect whether a graph contains a cycle
 */
public class CourseSchedule
{

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

		// Count number of prerequisites for each course
		int[] numPres = new int[numCourses];

		for (int[] pair : prerequisites)
		{
			numPres[pair[0]]++;
		}

		// Store nodes with no prerequisite into the queue
		// as start nodes
		ArrayDeque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < numCourses; i++)
		{
			if (numPres[i] == 0)
			{
				q.add(i);
			}
		}

		int numStart = q.size();

		// Do a bfs
		while (!q.isEmpty())
		{
			int currNode = q.remove();

			for (int[] pair : prerequisites)
			{
				if (pair[1] == currNode)
				{
					numPres[pair[0]]--;

					if (numPres[pair[0]] == 0)
					{
						q.add(pair[0]);
						numStart++;
					}
				}
			}
		}

		return numStart == numCourses;
	}
}
