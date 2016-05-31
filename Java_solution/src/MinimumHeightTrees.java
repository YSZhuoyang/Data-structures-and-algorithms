import java.util.*;


/**
 * Created by oscar on 5/31/16.
 */
public class MinimumHeightTrees
{

	// A very fast solution by removing leaves
	// See: http://buttercola.blogspot.com.au/2016/01/leetcode-minimum-height-trees.html
	// Complexity: n
	public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		List<Integer> result = new ArrayList<>();

		if (n <= 0 || edges == null)
		{
			return result;
		}

		// Corner case: there is a single node and no edge at all
		if (n == 1 && edges.length == 0)
		{
			result.add(0);
			return result;
		}

		// Step 1: construct the graph
		List<Set<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++)
		{
			adjList.add(new HashSet<>());
		}

		for (int[] edge : edges)
		{
			int from = edge[0];
			int to = edge[1];
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}

		// Remove leaf nodes
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++)
		{
			if (adjList.get(i).size() == 1)
			{
				leaves.add(i);
			}
		}

		while (n > 2)
		{
			// identify and remove all leaf nodes
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int leaf : leaves)
			{
				int neighbor = adjList.get(leaf).iterator().next();
				adjList.get(neighbor).remove(leaf);

				if (adjList.get(neighbor).size() == 1)
				{
					newLeaves.add(neighbor);
				}
			}

			leaves = newLeaves;
		}

		return leaves;
	}

	// My first solution using brute force and reconstructing graph,
	// which exceeds time limits
	// Complexity: n^2
	/*public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		List<Integer> list = new ArrayList<>();

		if (n <= 0 || edges == null || edges.length == 0)
		{
			return list;
		}

		int height;
		int min = edges.length;
		boolean[] visited = new boolean[n];
		HashMap<Integer, Integer> treeHeight = new HashMap<>(n);
		HashMap<Integer, List<Integer>> neighbourLists = new HashMap<>(n);
		List<Integer> neighbours;

		// Reconstruct the graph, and
		// find neighbours of each node
		for (int i = 0; i < n; i++)
		{
			neighbours = new ArrayList<>();

			for (int j = 0; j < edges.length; j++)
			{
				if (edges[j][0] == i)
				{
					neighbours.add(edges[j][1]);
				}
				else if (edges[j][1] == i)
				{
					neighbours.add(edges[j][0]);
				}
			}

			neighbourLists.put(i, neighbours);
		}

		// Find height of trees starts from every node
		for (int i = 0; i < n; i++)
		{
			height = findHeight(i, neighbourLists, visited);
			treeHeight.put(i, height);

			if (min > height)
			{
				min = height;
			}
		}

		// Find trees with minimum height
		for (int i = 0; i < n; i++)
		{
			if (treeHeight.get(i) == min)
			{
				list.add(i);
			}
		}

		return list;
	}

	private int findHeight(int root, HashMap<Integer, List<Integer>> neighbourLists, boolean[] visited)
	{
		int max = 0;
		visited[root] = true;
		List<Integer> neighbours = neighbourLists.get(root);

		for (int i : neighbours)
		{
			if (visited[i])
			{
				continue;
			}

			int height = findHeight(i, neighbourLists, visited) + 1;

			if (max < height)
			{
				max = height;
			}
		}

		visited[root] = false;

		return max;
	}*/

	// My first solution using brute force without reconstructing graph,
	// which exceeds time limits
	// Complexity: n^2
	/*public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		List<Integer> list = new ArrayList<>();

		if (n <= 0 || edges == null)
		{
			return list;
		}

		int min = edges.length;
		boolean[] edgesVisited = new boolean[edges.length];
		HashMap<Integer, Integer> treeHeight = new HashMap<>(n);

		for (int i = 0; i < n; i++)
		{
			int height = findHeight(i, edges, edgesVisited);
			treeHeight.put(i, height);

			if (min > height)
			{
				min = height;
			}
		}

		for (int i = 0; i < n; i++)
		{
			if (treeHeight.get(i) == min)
			{
				list.add(i);
			}
		}

		return list;
	}

	private int findHeight(int root, int[][] edges, boolean[] edgesVisited)
	{
		int max = -1;

		for (int i = 0; i < edges.length; i++)
		{
			if (!edgesVisited[i])
			{
				if (edges[i][0] == root)
				{
					edgesVisited[i] = true;
					int height = findHeight(edges[i][1], edges, edgesVisited);
					edgesVisited[i] = false;

					if (max < height)
					{
						max = height;
					}
				}
				else if (edges[i][1] == root)
				{
					edgesVisited[i] = true;
					int height = findHeight(edges[i][0], edges, edgesVisited);
					edgesVisited[i] = false;

					if (max < height)
					{
						max = height;
					}
				}
			}
		}

		return max + 1;
	}*/
}
