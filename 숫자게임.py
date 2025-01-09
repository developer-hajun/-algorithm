from collections import deque
def solution(A, B):
    A.sort(reverse=True)
    B.sort(reverse=True)
    q2 = deque(B)
    count = 0
    for i in A:
        if i<q2[0]:
            count+=1
            q2.popleft()
    return count