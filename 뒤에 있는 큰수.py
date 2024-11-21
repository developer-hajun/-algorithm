def solution(numbers):
    stack = []
    answer =[-1]*len(numbers)
    for i in range(len(numbers)):
        while stack and numbers[i]>stack[-1][0]:
            answer[stack[-1][1]] = numbers[i]
            stack.pop()
        stack.append([numbers[i],i])
    return answer