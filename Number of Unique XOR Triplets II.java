class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[] one = new boolean[MAX];
        boolean[] two = new boolean[MAX];
        boolean[] three = new boolean[MAX];

        for (int x : nums) {

            three[x] = true;

            for (int v = 0; v < MAX; v++) {
                if (two[v]) {
                    three[v ^ x] = true;
                }
            }

            for (int v = 0; v < MAX; v++) {
                if (one[v]) {
                    two[v ^ x] = true;
                }
            }

            two[0] = true;

            one[x] = true;
        }

        int answer = 0;

        for (boolean possible : three) {
            if (possible) {
                answer++;
            }
        }

        return answer;
    }
}
