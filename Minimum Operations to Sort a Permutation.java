class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[(zero + i) % n] != i) {
                increasing = false;
            }

            if (nums[(zero - i + n) % n] != i) {
                decreasing = false;
            }
        }

        if (increasing) {
            return zero;
        }

        if (decreasing) {
            int rotateThenReverse = (zero + 1) % n + 1;
            int reverseThenRotate = n - zero;

            return Math.min(rotateThenReverse, reverseThenRotate);
        }

        return -1;
    }
}
