class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int add = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                balance++;
            }else{
                if(balance>0){
                    balance--;
                }else{
                    add++;
                }
            }
        }
        return add + balance;
    }
}
