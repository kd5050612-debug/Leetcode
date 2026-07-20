class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;

        int[][] result = new int[m][n];
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                 int index = row * n + col;
                 int newIndex = (index + k) % total;
                 int newRow = newIndex/n;
                 int newCol = newIndex%n;
                 result[newRow][newCol] = grid[row][col];

            }
    }
    List<List<Integer>> answer = new ArrayList<>();
    for(int i = 0; i < m; i++){
        List<Integer> currentRow = new ArrayList<>();
        for(int j = 0; j < n; j++){
         currentRow.add(result[i][j]);
        }
        answer.add(currentRow);
    }
    return answer;
}
}
