import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created by oscar on 23/02/17.
 */
public class SerializeAndDeserializeBT
{
    // Encodes a tree to a single string.
    public String serialize(TreeNode root)
    {
        if (root == null) return "l";

        String l = serialize(root.left);
        String r = serialize(root.right);

        return root.val + "," + l + "," + r;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data)
    {
        if (data == null || data.isEmpty())
            return null;

        String[] valStrArr = data.split(",");
        ArrayDeque<String> q = new ArrayDeque<>();
        q.addAll(Arrays.asList(valStrArr));

        return deserialize(q);
    }

    private TreeNode deserialize(ArrayDeque<String> q)
    {
        if (q == null || q.isEmpty()) return null;

        String val = q.remove();

        if (val.equals("l")) return null;
        else
        {
            TreeNode n = new TreeNode(
                    Integer.parseInt(val));
            n.left = deserialize(q);
            n.right = deserialize(q);

            return n;
        }
    }
}
