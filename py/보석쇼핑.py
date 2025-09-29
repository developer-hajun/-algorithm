def solution(gems):
    cc = set(gems)
    dic = {}
    left = 0
    answer = []
    result = 100001
    for right in range(len(gems)):
        if gems[right] not in dic:
            dic[gems[right]] = 1
        else:
            dic[gems[right]] += 1
        while len(dic) == len(cc):
            if right - left < result:
                answer = [left, right]
                result = right - left
            dic[gems[left]] -= 1
            if dic[gems[left]] == 0:
                del (dic[gems[left]])
            left += 1
    return answer[0] + 1, answer[1] + 1

