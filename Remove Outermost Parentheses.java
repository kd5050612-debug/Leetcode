class Solution {
    public String removeOuterParentheses(String s) {
      StringBuilder answer = new StringBuilder();
      int depth = 0;

      for(char ch : s.toCharArray()){
        if(ch == '('){
            if(depth>0){
                answer.append(ch);
            }
            depth++;

        }else{
            depth--;
            if(depth>0){
                anser.append(ch);
            }
        }
      }  
      return answer.toString();
    }
}
