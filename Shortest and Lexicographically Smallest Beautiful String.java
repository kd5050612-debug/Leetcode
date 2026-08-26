class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String answer = "";
        for(int i = 0; i < s.length(); i++){
            int count = 0;
            for(int j = i; j < s.length();j++){
                if(s.charAt(j) == '1'){
                    count++;
                }
                if(count == k){
                    String current = s.substring(i, j+1);
                    if(answer.equals("") 
                    || current.length() < answer.length() ||(current.length() == answer.length() && current.compareTo(answer) < 0)){
                        answer = current;
                    }
                    break;
                }
            }
        }
        return answer;
    }
}
