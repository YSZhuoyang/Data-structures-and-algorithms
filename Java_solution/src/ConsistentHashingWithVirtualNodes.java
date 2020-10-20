public class Solution {
    private TreeMap<Integer, Integer> nodes; // To quickly identify which micro-shard the hash code belongs to
    private int[] codes; // Ranging from 0 - n - 1, that form a ring
    private int n;       // Total number of hash codes
    private int k;       // Number of virtual shards for each DB physical instance

    private static Solution s;

    private Solution(int n, int k) {
        nodes = new TreeMap<>();
        codes = new int[n];
        for (int i = 0; i < n; i++) codes[i] = i;
        this.n = n;
        this.k = k;
    }

    /*
     * @param n: a positive integer
     * @param k: a positive integer
     * @return: a Solution object
     */
    public static Solution create(int n, int k) {
        if (s == null) s = new Solution(n, k);

        return s;
    }

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        Random rn = new Random();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int numLeft = n - nodes.size();
            int r = rn.nextInt(numLeft);
            int picked = codes[r];
            // swap
            int t = codes[numLeft - 1];
            codes[numLeft - 1] = picked;
            codes[r] = t;
            // Add hashcodes of new machine micro shards
            nodes.put(picked, machine_id);
            res.add(picked);
        }

        return res;
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        Integer code = nodes.ceilingKey(hashcode);
        if (code == null) code = nodes.firstKey();

        return nodes.get(code);
    }
}
