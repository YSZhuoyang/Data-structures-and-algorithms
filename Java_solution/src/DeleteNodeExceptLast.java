/**
 * Created by oscar on 4/5/16.
 */
public class DeleteNodeExceptLast
{

    // My solution
    public void deleteNode(ListNode node)
    {
        if (node == null || node.next == null)
        {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
