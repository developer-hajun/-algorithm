def solution(picks, minerals):
    sums = sum(picks)
    # 캘 수 있는 광물의 개수
    num_min = sums * 5
    minerals = minerals[:num_min]
    cnt_min = [[0, 0, 0] for x in range(len(minerals) // 5 + 1)]
    for i in range(len(minerals)):
        if minerals[i] == 'diamond':
            cnt_min[i // 5][0] += 1
        elif minerals[i] == 'iron':
            cnt_min[i // 5][1] += 1
        else:
            cnt_min[i // 5][2] += 1
    cnt_min.sort(key=lambda x: [-x[0], -x[1], -x[2]])
    answer = 0

    for a, b, c in cnt_min:
        if picks[0] > 0:
            answer += sum([a, b, c])
            picks[0] -= 1
        elif picks[1] > 0:
            answer += a * 5 + sum([b, c])
            picks[1] -= 1
        elif picks[2] > 0:
            answer += a * 25 + b * 5 + c
            picks[2] -= 1
    return answer


