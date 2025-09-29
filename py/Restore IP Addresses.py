from itertools import product


class Solution:

    def restoreIpAddresses(self, s: str) -> List[str]:
        answer = []
        for i in product([1, 2, 3], repeat=4):
            if sum(i) != len(s):
                continue
            ans = []
            count = 0
            for value in i:
                ans.append(s[count:count + value])
                count += value
            check = True
            for value in ans:
                if value == '0':
                    continue
                if int(value) > 255:
                    check = False
                    break
                else:
                    nums = True
                    for i in value.strip():
                        if i == '0' and nums:
                            check = False
                            break
                        else:
                            break
            if check:
                answer.append('.'.join(ans))
        return answer
