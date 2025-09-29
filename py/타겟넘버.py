from itertools import product


def solution(numbers, target):
    global answer
    answer = 0

    def dfs(depth, value):
        global answer
        if depth == len(numbers):
            if value == target:
                answer += 1
            return
        dfs(depth + 1, value - numbers[depth])
        dfs(depth + 1, value + numbers[depth])

    dfs(0, 0)
    return answer