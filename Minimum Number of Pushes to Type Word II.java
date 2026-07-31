import java.util.*;

class Solution {
    public int minimumPushes(String word) {

        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int answer = 0;

        for (int i = 25; i >= 0; i--) {

            if (freq[i] == 0) {
                continue;
            }

            int cost = (25 - i) / 8 + 1;

            answer += freq[i] * cost;
        }

        return answer;
    }
}
