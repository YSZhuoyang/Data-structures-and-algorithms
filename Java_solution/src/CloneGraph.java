import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by oscar on 7/5/16.
 */
public class CloneGraph
{
	HashMap<Integer, UndirectedGraphNode> memo = new HashMap<>();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node)
	{
		if (node == null)
		{
			return null;
		}
		else if (memo.get(node.label) != null)
		{
			return memo.get(node.label);
		}

		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		memo.put(node.label, newNode);

		List<UndirectedGraphNode> newList = new ArrayList<>(node.neighbors.size());

		for (UndirectedGraphNode neighbor : node.neighbors)
		{
			newList.add(cloneGraph(neighbor));
		}

		newNode.neighbors = newList;

		return newNode;
	}

	private class UndirectedGraphNode
	{
	    int label;
	    List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x)
	    {
		    label = x; neighbors = new ArrayList<>();
	    }
	}
}
