class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = (n)*(2*1 + (n-1)*2)/2;
        int sumEven = (n)*(2*2 + (n-1)*2)/2;
        return gcd(sumOdd, sumEven);
    }
    int gcd(int a, int b){
        if(b==0)
        return a;
        else return gcd(b, a%b);
    }
}
