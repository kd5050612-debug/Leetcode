import java.util.Arrays;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        if (n == 0)
            return new int[]{};

        int[][] temp = new int[n][2];

        // Store value and original index
        for (int i = 0; i < n; i++) {
            temp[i][0] = arr[i];
            temp[i][1] = i;
        }

        // Sort according to value
        Arrays.sort(temp, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[n];

        int rank = 1;
        int prev = temp[0][0];
        int ind = temp[0][1];

        ans[ind] = rank;

        for (int i = 1; i < n; i++) {
            int val = temp[i][0];
            int idx = temp[i][1];

            if (val != prev)
                rank++;

            ans[idx] = rank;
            prev = val;
        }

        return ans;
    }
}
