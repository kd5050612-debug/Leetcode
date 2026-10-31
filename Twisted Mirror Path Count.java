class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1_000_000_007;

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                long ways = dp[i][j];

                if (ways == 0) {
                    continue;
                }

                if (j + 1 < n) {
                    int r = i;
                    int c = j + 1;

                    while (r < m && c < n && grid[r][c] == 1) {
                        r++;
                    }

                    if (r < m && c < n) {
                        dp[r][c] = (dp[r][c] + ways) % mod;
                    }
                }

                if (i + 1 < m) {
                    int r = i + 1;
                    int c = j;

                    while (r < m && c < n && grid[r][c] == 1) {
                        c++;
                    }

                    if (r < m && c < n) {
                        dp[r][c] = (dp[r][c] + ways) % mod;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
