class Solution {
    public int missingInteger(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int sum = nums[0];
        for(int i = 1; i<nums.length; i++){
            if(nums[i] != nums[i - 1]+1){
                break;
            }
            sum += nums[i];
        }
            int answer = sum;
            while(set.contains(answer)){
                answer++;
            }
            return answer;
        }
    }
