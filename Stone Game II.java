class Solution {
    public int stoneGameII(int[] piles) {

        int n = piles.length;
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {

            for (int M = n; M >= 1; M--) {

                int best = 0;

                int maxTake = Math.min(2 * M, n - i);

                for (int X = 1; X <= maxTake; X++) {

                    int newM = Math.max(M, X);

                    int current = suffix[i] - dp[i + X][newM];

                    best = Math.max(best, current);
                }

                dp[i][M] = best;
            }
        }

        return dp[0][1];
    }
}
