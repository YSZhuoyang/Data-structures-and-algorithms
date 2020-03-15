/**
 * Given k eggs, a building with n floors, find out the
 * highest floor where an egg being dropped will not break
 * (an unrealistic problem but a good practice for binary search)
 * eggs can be picked up if they do not break.
 *
 * Constraints:
 * 1 <= K <= 100
 * 1 <= N <= 10000
 */
class Solution {
	// class Key {
    //     public Integer x;
    //     public Integer k;
        
    //     public Key(int x, int k) {
    //         this.x = x;
    //         this.k = k;
    //     }

    //     @Override
    //     public int hashCode() {
    //         return x.hashCode() ^ k.hashCode();
    //     }

    //     @Override
    //     public boolean equals(Object o) {
    //         Key other = (Key)o;
    //         return x == other.x && k == other.k;
    //     }
    // }

    private HashMap<Integer, Integer> memo = new HashMap<>();

    public int superEggDrop(int K, int N) {
        // Idea:
        // 1. Divide and conquer with substructure (let function numThrow takes 2 params: N floors, K eggs
        //    and return mininum number of steps):
        //      numThrow(N, K) = min( max( numThrow(X - 1, K - 1), numThrow(N - X, K - 1) ) | for all X in [1, N] )
        //      numThrow(N, 1) = N
        //
        // 2. Memorization
        // 3. Bsearch
        //
        // Time: O(N * K * log(N))

        // Start throwing from the 1st floor until Fth floor is found if there's only 1 egg left
        if (K == 1) return N;
        else if (N == 1) return 1;
        else if (N == 0) return 0;

		// Using a Tuple class object as key is a lot slower than using an integer based on given constraints
        int key = K * 100000 + N;
        if (memo.containsKey(key)) return memo.get(key);

        int minThrow = N;
        // Bsearch for min of max(F(K - 1, X - 1), F(K, N - X)) based on the fact:
		//     F(K - 1, X - 1) monotonically increases
		//     F(K, N - X) monotonically decreases with respect to X
		// thus:
		//     the part on the left of cross point satisfies condition: F(K - 1, X - 1) < F(K, N - X)
		//     the part on the right of cross point satisfies condition: F(K - 1, X - 1) > F(K, N - X)
        int l = 1;
        int r = N;
        while (l < r) {
            int x = (int) ((l + r) / 2);
            // Throw an egg at the Xth floor
            // Egg broke thus F < x
            int numThrowBeforeX = superEggDrop(K - 1, x - 1); // Monotonically inc
            // Egg did not broke thus F >= x
            int numThrowAfterX = superEggDrop(K, N - x);      // Monotonically dec
            // Pick the worst case given F can be any floor
            int numThrow = Math.max(numThrowBeforeX, numThrowAfterX) + 1;
            minThrow = Math.min(minThrow, numThrow);
            if (numThrowBeforeX < numThrowAfterX)
                l = x + 1; // Go right
            else
                r = x; // Go left;
        }

        memo.put(key, minThrow);

        return minThrow;
    }
}
