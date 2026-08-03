class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        Integer[] dp = new Integer[n];
        int diff = solve(0, stoneValue,dp);
        if(diff > 0) return "Alice";
        if(diff < 0) return "Bob";
        return "Tie";
    }
    private int solve(int i, int[] stoneValue, Integer[] dp){
        if(i >= stoneValue.length)
        return 0;
        if(dp[i] != null)
        return dp[i];

        int sum = 0;
        int best = Integer.MIN_VALUE;

        for(int take = 1; take <= 3 && i + take - 1 < stoneValue.length; take++){
            sum += stoneValue[i + take - 1];
            best = Math.max(
                best,
                sum - solve(i + take, stoneValue, dp)
            );
        }
        return dp[i] = best;
    }
}
