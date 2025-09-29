class Solution:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        left_size = 0
        left_sum = 0
        right_size = len(nums)
        right_sum = sum(nums)
        answer = []
        for i in nums:
            value = abs(i*left_size-left_sum)+abs(i*(right_size-1)-(right_sum-i))
            answer.append(abs(value))
            left_size+=1
            left_sum+=i
            right_size-=1
            right_sum-=i
        return answer