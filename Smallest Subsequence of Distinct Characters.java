class Solution {
    public String smallestSubsequence(String s) {
        int[] lastOccurrence = new int[26];
        for(int i=0; i < s.length(); i++){
            lastOccurrence[s.charAt(i)-'a'] = i;
                    }
                    boolean[] visited = new boolean[26];
                    Stack<Character> stack = new Stack<>();
                    for(int i=0; i < s.length(); i++){
           char current = s.charAt(i);
           if(visited [current - 'a']){
            continue;
           } 
           while(!stack.isEmpty()
            && current < stack.peek()
             && lastOccurrence[stack.peek() - 'a']>i){
            visited[stack.pop() - 'a'] = false;
           } stack.push(current);
           visited[current - 'a'] = true;
                    }
                    StringBuilder ans = new StringBuilder();
                    for(char c : stack){
                        ans.append(c);
                    }
                    return ans.toString();
    }
}
