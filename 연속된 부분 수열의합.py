def solution(sequence, k):
    left = right = 0
    value = sequence[0]
    answer = []

    interval_sum = 0
    end = 0
    for start in range(len(sequence)):
        while interval_sum < k and end < len(sequence):
            interval_sum += sequence[end]
            end += 1
        if interval_sum == k:
            answer.append([start, end - 1])
        interval_sum -= sequence[start]
    answer.sort(key=lambda x: [x[1] - x[0]])
    return answer[0]