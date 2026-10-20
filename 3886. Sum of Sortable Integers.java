
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                if (!isRotation(nums, sorted, start, k)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                answer += k;
            }
        }

        return answer;
    }

    private boolean isRotation(int[] nums, int[] sorted, int start, int k) {
        for (int shift = 0; shift < k; shift++) {
            boolean match = true;

            for (int i = 0; i < k; i++) {
                if (nums[start + (i + shift) % k] != sorted[start + i]) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return true;
            }
        }

        return false;
    }
}
