class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        for(int num : nums){
            if(num<min){
                min = num;
            } if(num>max){
                max = num;
            }
        } while(max % min != 0){
            int remainder = max % min;
            max = min;
            min = remainder;
        }
        return min;
    }
}
