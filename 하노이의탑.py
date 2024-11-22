answer = []


def Hanoi(fr, to, temp, n):
    if n == 1:
        answer.append([fr, to])
    else:
        Hanoi(fr, temp, to, n - 1)
        answer.append([fr, to])
        Hanoi(temp, to, fr, n - 1)


def solution(n):
    Hanoi(1, 3, 2, n)
    return answer