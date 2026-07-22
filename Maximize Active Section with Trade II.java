import java.util.*;

class Solution {

    static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    static class SparseTable {
        private final int[][] st;
        private final int[] log;

        SparseTable(int[] arr) {
            int n = arr.length;

            log = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                log[i] = log[i / 2] + 1;
            }

            int levels = log[n] + 1;
            st = new int[levels][n];

            System.arraycopy(arr, 0, st[0], 0, n);

            for (int level = 1; level < levels; level++) {
                int len = 1 << level;
                int half = len >> 1;

                for (int i = 0; i + len <= n; i++) {
                    st[level][i] = Math.max(
                            st[level - 1][i],
                            st[level - 1][i + half]
                    );
                }
            }
        }

        int query(int left, int right) {
            int len = right - left + 1;
            int level = log[len];

            return Math.max(
                    st[level][left],
                    st[level][right - (1 << level) + 1]
            );
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(
            String s,
            int[][] queries
    ) {
        int n = s.length();

        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];

        Arrays.fill(zeroGroupIndex, -1);

        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == '0') {

                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }

            zeroGroupIndex[i] = zeroGroups.size() - 1;
        }

        if (zeroGroups.isEmpty()) {
            List<Integer> answer = new ArrayList<>();

            for (int i = 0; i < queries.length; i++) {
                answer.add(totalOnes);
            }

            return answer;
        }

        int m = zeroGroups.size() - 1;
        int[] merge = new int[m];

        for (int i = 0; i < m; i++) {
            merge[i] =
                    zeroGroups.get(i).length
                    + zeroGroups.get(i + 1).length;
        }

        SparseTable sparseTable = new SparseTable(merge);

        List<Integer> answer = new ArrayList<>();

        for (int[] query : queries) {

            int l = query[0];
            int r = query[1];

            int leftGroup = zeroGroupIndex[l];
            int rightGroup = zeroGroupIndex[r];

            int leftPart = -1;

            if (leftGroup != -1) {
                Group g = zeroGroups.get(leftGroup);

                leftPart = g.start + g.length - l;
            }

            int rightPart = -1;

            if (rightGroup != -1) {
                Group g = zeroGroups.get(rightGroup);

                rightPart = r - g.start + 1;
            }

            int startAdjacent = leftGroup + 1;

            int endGroup =
                    (s.charAt(r) == '1')
                            ? rightGroup
                            : rightGroup - 1;

            int endAdjacent = endGroup - 1;

            int best = totalOnes;

            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && leftGroup + 1 == rightGroup) {

                best = Math.max(
                        best,
                        totalOnes + leftPart + rightPart
                );
            }

            else if (startAdjacent <= endAdjacent) {

                best = Math.max(
                        best,
                        totalOnes
                                + sparseTable.query(
                                        startAdjacent,
                                        endAdjacent
                                )
                );
            }

            if (s.charAt(l) == '0'
                    && leftGroup + 1 <= endGroup) {

                best = Math.max(
                        best,
                        totalOnes
                                + leftPart
                                + zeroGroups.get(leftGroup + 1).length
                );
            }

            if (s.charAt(r) == '0'
                    && leftGroup < rightGroup - 1) {

                best = Math.max(
                        best,
                        totalOnes
                                + rightPart
                                + zeroGroups.get(rightGroup - 1).length
                );
            }

            answer.add(best);
        }

        return answer;
    }
}
