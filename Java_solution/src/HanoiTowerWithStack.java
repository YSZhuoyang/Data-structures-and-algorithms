import java.util.Stack;

/**
 * Created by oscar on 6/29/16.
 *
 * sudo code (move n plates from s1 to s2 using s3):
 * n == 1:
 * move 1st from s1 to s2
 * n > 1:
 * move n - 1 plates from s1 to s3
 * move 1st from s1 to s2
 * move n - 1 plates from s3 to s2
 */
public class HanoiTowerWithStack
{

	public void move(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> media)
	{
		if (n <= 0)
		{
			return;
		}
		else if (n == 1)
		{
			to.push(from.pop());
		}
		else
		{
			move(n - 1, from, media, to);
			move(1, from, to, media);
			move(n - 1, media, to, from);
		}
	}
}
