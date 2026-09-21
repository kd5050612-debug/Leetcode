class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] answer = new long[k];
        long[] dp = new long[k];

        for(int num : nums){
            long[] next = new long[k];

            int value = num % k;
            next[value]++;

            for(int r = 0; r < k; r++){
                int newRemainder = (r * value) % k;
                next[newRemainder] += dp[r];
            }
            for(int r = 0; r < k; r++){
                answer[r] += next[r];
            }
            dp = next;
        }
        return answer;
    }
}
