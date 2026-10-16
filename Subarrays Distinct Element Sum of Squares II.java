
class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum, square, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int previous = last[value];

            update(1, 0, n - 1, previous + 1, i);
            last[value] = i;

            answer = (answer + square[1]) % MOD;
        }

        return (int) answer;
    }

    void apply(int node, int left, int right, long value) {
        long length = right - left + 1L;

        square[node] = (square[node]
                + 2 * value % MOD * sum[node] % MOD
                + value * value % MOD * length % MOD) % MOD;

        sum[node] = (sum[node] + value * length) % MOD;
        lazy[node] = (lazy[node] + value) % MOD;
    }

    void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) return;

        int mid = (left + right) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    void update(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) return;

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);

        int mid = (left + right) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }
}
