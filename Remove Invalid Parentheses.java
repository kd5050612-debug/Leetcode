
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> answer = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                String current = queue.poll();

                if (isValid(current)) {
                    answer.add(current);
                    found = true;
                }

                if (found) {
                    continue;
                }

                for (int i = 0; i < current.length(); i++) {
                    if (current.charAt(i) != '(' && current.charAt(i) != ')') {
                        continue;
                    }

                    String next = current.substring(0, i)
                            + current.substring(i + 1);

                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            if (found) {
                break;
            }
        }

        return answer;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}
