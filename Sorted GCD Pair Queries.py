class Solution:
    def gcdValues(self, nums: List[int], queries: List[int]) -> List[int]:
        m = max(nums)


        freq = [0] * (m + 1)
        for num in nums:
            freq[num] += 1

  
        cnt = [0] * (m + 1)
        for i in range(1, m + 1):
            for j in range(i, m + 1, i):
                cnt[i] += freq[j]

 
        for i in range(1, m + 1):
            cnt[i] = cnt[i] * (cnt[i] - 1) // 2


        for i in range(m, 0, -1):
            for j in range(i * 2, m + 1, i):
                cnt[i] -= cnt[j]


        prefix = [0] * (m + 1)
        for i in range(1, m + 1):
            prefix[i] = prefix[i - 1] + cnt[i]

        ans = []
        for q in queries:
            ans.append(bisect_left(prefix, q + 1))

        return ans
