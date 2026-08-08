class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] next = new int[n + 1];
        java.util.Arrays.fill(next, n);
        int j = m - 1;

        for(int i = n - 1; i >=0; i--){
            next[i] = next[i + 1];

if(j >= 0 && word1.charAt(i) == word2.charAt(j)){
    next[i] = i;
    j--;
}        }

int[] ans = new int[m];
int i = 0;
int mismatches = 0;

for (j = 0; j < m; j++){
    boolean found = false;
    while(i < n){
        boolean same = word1.charAt(i) == word2.charAt(j);
        if(same){
            ans[j] =i;
            i++;
            found = true;
            break;
        }
        if(mismatches == 0){
            int remaining = m - j - 1;
            int pos = i + 1;
            int count = 0;
            int k = j + 1;

            while(pos < n && k < m){
                if(word1.charAt(pos) == word2.charAt(k)){
                    count++;
                    k++;
                }
                pos++;
            }
            if(count == remaining){
                ans[j] = i;
                i++;
                mismatches = 1;
                found = true;
                break;
            }
        }
        i++;

    }
    if(!found){
        return new int[0];
    }
}
return ans;

    }
}
