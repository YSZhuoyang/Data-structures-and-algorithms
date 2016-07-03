import java.util.Stack;

/**
 * Created by oscar on 6/30/16.
 *
 * Sort a stack without knowing its internal implementation.
 *
 * On the other hand, if we know the internal implementation, sorting
 * can be further optimized (e.g. quicksort the array or linkedlist which
 * implement this stack).
 * The idea is creating and returning another stack.
 */
public class SortStack
{

	public Stack<Integer> sortStack(Stack<Integer> s)
	{
		if (s == null || s.isEmpty())
		{
			return s;
		}

		Stack<Integer> newStack = new Stack<>();
		int temp;

		while (!s.isEmpty())
		{
			temp = s.pop();

			while (!newStack.isEmpty() && temp > newStack.peek())
			{
				s.push(newStack.pop());
			}

			newStack.push(temp);
		}

		return newStack;
	}
}
