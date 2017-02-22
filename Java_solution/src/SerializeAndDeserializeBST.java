/**
 * Created by oscar on 22/02/17.
 */
public class SerializeAndDeserializeBST
{
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        String l = serialize(root.left);
        String r = serialize(root.right);

        return root.val + "," + l + "," + r;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty())
            return null;

        String[] numStrArr = data.split(",");
        int len = numStrArr.length;
        int id = 0;
        int[] numArr = new int[len];

        for (int i = 0; i < len; i++)
            if (!numStrArr[i].isEmpty())
                numArr[id++] = Integer.parseInt(numStrArr[i]);

        return deserialize(numArr, 0, id - 1);
    }

    private TreeNode deserialize(int[] data, int start, int end)
    {
        if (start > end)
            return null;
        else if (start == end)
            return new TreeNode(data[start]);

        TreeNode node = new TreeNode(data[start]);

        for (int i = start + 1; i <= end; i++)
            if (data[i] > data[start])
            {
                node.left = deserialize(data, start + 1, i - 1);
                node.right = deserialize(data, i, end);

                return node;
            }

        node.left = deserialize(data, start + 1, end);
        node.right = null;

        return node;
    }
}
