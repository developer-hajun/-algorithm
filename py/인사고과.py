def solution(scores):
    target = scores[0].copy()
    scores.sort(key = lambda x:[-x[0],x[1]])
    maxb = 0
    answer=0
    for a, b in scores:
        if target[0] < a and target[1] < b:
            return -1
        if b >= maxb:
            maxb = b
            if a + b > target[0]+target[1]:
                answer += 1
    return answer + 1