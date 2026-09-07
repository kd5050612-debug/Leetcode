class Solution {
    public int distinctSubseqII(String s) {
        long mod = 1000000007;
        long[] last = new long[26];
        long total = 0;

        for( char c : s.toCharArray()){
            int index = c - 'a';
            long newSubseq = (total + 1) % mod;
            total = (total + newSubseq - last[index] + mod)% mod;
last[index] = newSubseq;

        }
        return(int)total;
    }
}
