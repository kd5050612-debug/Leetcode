class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();
        int mod = 1000000007;

        long[] presum = new long[n + 1];
        long[] conval = new long[n + 1];
        int[] count = new int[n + 1];
        long[] pow10 = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            int num = s.charAt(i - 1) - '0';
            presum[i] = (presum[i - 1] + num) % mod;

            if (num == 0) {
                conval[i] = conval[i - 1];
                count[i] = count[i - 1];
            } else {
                conval[i] = (conval[i - 1] * 10 + num) % mod;
                count[i] = count[i - 1] + 1;
            }
        }

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            long sum = (presum[right + 1] - presum[left] + mod) % mod;

            long leftpart = conval[left];
            long rightpart = conval[right + 1];

            int diff = count[right + 1] - count[left];
            long power = pow10[diff];

            long range = (rightpart - (leftpart * power) % mod + mod) % mod;

            long x = (range * sum) % mod;
            ans[i] = (int) x;
        }

        return ans;
    }
}
