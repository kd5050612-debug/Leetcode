class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        int m = n / 2;
        int[] count = half.clone();

        StringBuilder left = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int x = target.charAt(i) - 'a';

            if (count[x] == 0) {
                break;
            }

            count[x]--;
            left.append(target.charAt(i));
        }

        if (left.length() == m) {
            if (n % 2 == 1) {
                int targetMid = target.charAt(m) - 'a';

                if (mid > targetMid) {
                    return makePalindrome(left.toString(), mid);
                }
            }

            for (int i = m - 1; i >= 0; i--) {
                int x = target.charAt(i) - 'a';
                count[x]++;

                for (int c = x + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--;

                        StringBuilder next = new StringBuilder();

                        for (int j = 0; j < i; j++) {
                            next.append(target.charAt(j));
                        }

                        next.append((char) ('a' + c));

                        for (int j = 0; j < 26; j++) {
                            while (count[j] > 0) {
                                next.append((char) ('a' + j));
                                count[j]--;
                            }
                        }

                        return makePalindrome(next.toString(), mid);
                    }
                }
            }

            return "";
        }

        int pos = left.length();

        for (int c = target.charAt(pos) - 'a' + 1; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;

                StringBuilder next = new StringBuilder(left);
                next.append((char) ('a' + c));

                for (int j = 0; j < 26; j++) {
                    while (count[j] > 0) {
                        next.append((char) ('a' + j));
                        count[j]--;
                    }
                }

                return makePalindrome(next.toString(), mid);
            }
        }

        for (int i = pos - 1; i >= 0; i--) {
            int x = target.charAt(i) - 'a';
            count[x]++;

            for (int c = x + 1; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;

                    StringBuilder next = new StringBuilder();

                    for (int j = 0; j < i; j++) {
                        next.append(target.charAt(j));
                    }

                    next.append((char) ('a' + c));

                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            next.append((char) ('a' + j));
                            count[j]--;
                        }
                    }

                    return makePalindrome(next.toString(), mid);
                }
            }
        }

        return "";
    }

    private String makePalindrome(String left, int mid) {
        StringBuilder ans = new StringBuilder(left);

        if (mid != -1) {
            ans.append((char) ('a' + mid));
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}
