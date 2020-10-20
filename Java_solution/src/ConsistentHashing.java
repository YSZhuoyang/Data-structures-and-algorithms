public class Solution {
    // (left, right, machine id)
    private PriorityQueue<int[]> q = new PriorityQueue<>((int[] a, int[] b) -> {
        int sa = a[1] - a[0] + 1;
        int sb = b[1] - b[0] + 1;
        if (sb == sa) return a[2] - b[2];

        return sb - sa;
    });

    /*
     * @param n: a positive integer
     * @return: n x 3 matrix
     */
    public List<List<Integer>> consistentHashing(int n) {
        q.add(new int[]{ 0, 359, 1 });
        for (int i = 0; i < n - 1; i++) {
            int[] node = q.poll();
            int mId = node[2];
            int l = node[0];
            int r = node[1];
            int m = (int) ((l + r) / 2);
            q.add(new int[]{ l, m, mId });
            q.add(new int[]{ m + 1, r, q.size() + 1 });
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] node = q.poll();
            List<Integer> newList = new ArrayList<>();
            newList.add(node[0]);
            newList.add(node[1]);
            newList.add(node[2]);
            res.add(newList);
        }

        return res;
    }
}
