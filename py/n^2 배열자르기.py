def solution(n, left, right):
    answer = []
    for i in range(left,right+1):
        y = i//n
        x = i%n
        answer.append(max(y,x)+1)
    return answer