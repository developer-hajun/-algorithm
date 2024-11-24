def solution(k, ranges):
    answer = [k]
    now = k
    while now > 1:
        if now % 2 == 0:
            now //= 2
        else:
            now = (now * 3) + 1
        answer.append(now)
    answer2 = []
    for i in range(1, len(answer)):
        left, right = answer[i - 1], answer[i]
        sq = min(left, right)
        tr = abs(left - right) * 0.5
        answer2.append(sq + tr)
    n = len(answer) - 1

    result = []
    for a, b in ranges:
        if a > n + b:
            result.append(-1)
        else:
            result.append(sum(answer2[a:n + b]))
    return result