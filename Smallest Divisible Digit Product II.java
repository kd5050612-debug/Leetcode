
class Solution {
    public String smallestNumber(String num, long t) {
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long tempT = t;

        while (tempT % 2 == 0) { c2++; tempT /= 2; }
        while (tempT % 3 == 0) { c3++; tempT /= 3; }
        while (tempT % 5 == 0) { c5++; tempT /= 5; }
        while (tempT % 7 == 0) { c7++; tempT /= 7; }

        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = num.charAt(i) - '0';
        }

        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                firstZero = i;
                break;
            }
        }

        if (firstZero == -1 && isValid(digits, n, c2, c3, c5, c7)) {
            return num;
        }

        int limit = (firstZero == -1) ? n : firstZero + 1;

        for (int i = limit - 1; i >= 0; i--) {
            int startDigit = digits[i] + 1;
            
            for (int d = startDigit; d <= 9; d++) {
                int[] candidate = Arrays.copyOf(digits, n);
                candidate[i] = d;

              
                if (canFormValidSuffix(candidate, i, c2, c3, c5, c7)) {
                    fillSuffix(candidate, i, c2, c3, c5, c7);
                    
                    StringBuilder sb = new StringBuilder();
                    for (int x : candidate) {
                        sb.append(x);
                    }
                    return sb.toString();
                }
            }
        }

        return createMinimumNumber(n + 1, c2, c3, c5, c7);
    }

    private boolean isValid(int[] digits, int len, int c2, int c3, int c5, int c7) {
        int r2 = 0, r3 = 0, r5 = 0, r7 = 0;
        for (int i = 0; i < len; i++) {
            int d = digits[i];
            if (d == 0) return false;
            while (d % 2 == 0) { r2++; d /= 2; }
            while (d % 3 == 0) { r3++; d /= 3; }
            while (d % 5 == 0) { r5++; d /= 5; }
            while (d % 7 == 0) { r7++; d /= 7; }
        }
        return r2 >= c2 && r3 >= c3 && r5 >= c5 && r7 >= c7;
    }

    private boolean canFormValidSuffix(int[] digits, int pivot, int c2, int c3, int c5, int c7) {
        int r2 = 0, r3 = 0, r5 = 0, r7 = 0;
        for (int i = 0; i <= pivot; i++) {
            int d = digits[i];
            while (d % 2 == 0) { r2++; d /= 2; }
            while (d % 3 == 0) { r3++; d /= 3; }
            while (d % 5 == 0) { r5++; d /= 5; }
            while (d % 7 == 0) { r7++; d /= 7; }
        }

        int rem2 = Math.max(0, c2 - r2);
        int rem3 = Math.max(0, c3 - r3);
        int rem5 = Math.max(0, c5 - r5);
        int rem7 = Math.max(0, c7 - r7);

        int slotsNeeded = rem7 + rem5 + (rem3 + 1) / 2 + (rem2 + 2) / 3;
        int slotsAvailable = digits.length - 1 - pivot;

        return slotsNeeded <= slotsAvailable;
    }

    private void fillSuffix(int[] digits, int pivot, int c2, int c3, int c5, int c7) {
        int r2 = 0, r3 = 0, r5 = 0, r7 = 0;
        for (int i = 0; i <= pivot; i++) {
            int d = digits[i];
            while (d % 2 == 0) { r2++; d /= 2; }
            while (d % 3 == 0) { r3++; d /= 3; }
            while (d % 5 == 0) { r5++; d /= 5; }
            while (d % 7 == 0) { r7++; d /= 7; }
        }

        int rem2 = Math.max(0, c2 - r2);
        int rem3 = Math.max(0, c3 - r3);
        int rem5 = Math.max(0, c5 - r5);
        int rem7 = Math.max(0, c7 - r7);

        int idx = digits.length - 1;

        while (rem7 > 0) { digits[idx--] = 7; rem7--; }
        while (rem5 > 0) { digits[idx--] = 5; rem5--; }

        while (rem3 >= 2) { digits[idx--] = 9; rem3 -= 2; }
        while (rem2 >= 3) { digits[idx--] = 8; rem2 -= 3; }

        if (rem3 == 1 && rem2 >= 2) { digits[idx--] = 6; rem3 -= 1; rem2 -= 2; }
        else if (rem3 == 1) { digits[idx--] = 3; rem3 -= 1; }

        if (rem2 == 2) { digits[idx--] = 4; rem2 -= 2; }
        else if (rem2 == 1) { digits[idx--] = 2; rem2 -= 1; }

        while (idx > pivot) {
            digits[idx--] = 1;
        }

        Arrays.sort(digits, pivot + 1, digits.length);
    }

    private String createMinimumNumber(int len, int c2, int c3, int c5, int c7) {
        int[] digits = new int[len];
        Arrays.fill(digits, 1);

        int idx = len - 1;
        while (c7 > 0) { digits[idx--] = 7; c7--; }
        while (c5 > 0) { digits[idx--] = 5; c5--; }

        while (c3 >= 2) { digits[idx--] = 9; c3 -= 2; }
        while (c2 >= 3) { digits[idx--] = 8; c2 -= 3; }

        if (c3 == 1 && c2 >= 2) { digits[idx--] = 6; c3 -= 1; c2 -= 2; }
        else if (c3 == 1) { digits[idx--] = 3; c3 -= 1; }

        if (c2 == 2) { digits[idx--] = 4; c2 -= 2; }
        else if (c2 == 1) { digits[idx--] = 2; c2 -= 1; }

        if (idx < -1) return "-1"; 

        Arrays.sort(digits);
        StringBuilder sb = new StringBuilder();
        for (int x : digits) {
            sb.append(x);
        }
        return sb.toString();
    }
}
