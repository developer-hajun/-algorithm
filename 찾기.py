import sys
from collections import deque
def FailureFunction(pt):
    F[0] = 0
    i = 1
    j = 0
    while i<len(pt):
        if pt[i] == pt[j]:
            F[i] = j + 1
            i +=1
            j +=1
        elif j > 0:
            j = F[j - 1]
        else:
            F[j] = 0
            i+=1
st = input()
now = input()
F = [0]*len(now)
FailureFunction(now)
print(F)
results = []
pidx = 0
for i in range(len(st)):
    while pidx>0 and st[i] != now[pidx]:
        pidx = F[pidx - 1] #맞지않을때 어디서부터 비교할지 정하는부분
    if st[i] == now[pidx]:#같을때
        if pidx == len(now) - 1:
            results.append(i - len(now) + 2)
            pidx = F[pidx] #끝난후 다시 시작할 부분
        else:
            pidx += 1
print(len(results))
for i in results:
    print(i,end=' ')