def solution(enroll, referral, seller, amount):
    answer = {i: 0 for i in enroll}
    dic = {i: [] for i in enroll}
    for i in range(len(referral)):
        if referral[i] == '-':
            continue
        dic[enroll[i]].append(referral[i])

    def dfs(now, value):
        if value < 10:
            answer[now] += value
            return
        give = value // 10
        eat = value - give
        if not dic[now]:
            answer[now] += eat
            return
        answer[now] += eat
        dfs(dic[now][0], give)

    for i in range(len(seller)):
        dfs(seller[i], amount[i] * 100)
    result = []
    for i in answer:
        result.append(answer[i])
    return result
