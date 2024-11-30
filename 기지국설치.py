import math
def solution(n, stations, w):
    answer = 0
    idx = 1
    for i in stations:
        a,b = i-w,i+w
        if a<1:
            a=1
        if b>n:
            b=n
        answer +=math.ceil((a-idx)/(1+w*2))
        idx = b+1
    if idx<=n:
        if idx==n:
            answer+=1
        else:
            answer +=math.ceil((n-idx)/(1+w*2))
    return answer