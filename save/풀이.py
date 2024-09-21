from collections import deque
from itertools import permutations

n = int(input())
ling = [[0]+list(map(int, input().split())) for _ in range(n)]
answer = -1

for human in permutations([2,3,4,5,6,7,8,9],8):
    h = deque(list(human[:3])+[1]+list(human[3:]))
    value = 0
    for now_ling in ling:
        out = 0
        p1 = p2 = p3 = 0
        while out<3:
            taza = h.popleft()
            command = now_ling[taza]
            if command==0:
                out+=1
            elif command==1:
                value +=p3
                p1,p2,p3 = 1,p1,p2
            elif command==2:
                value += p3+p2
                p1, p2, p3 = 0, 1, p1
            elif command==3:
                value += p3 + p2+p1
                p1, p2, p3 = 0, 0,1
            elif command==4:
                value += p3 + p2 + p1+1
                p1, p2, p3 = 0,0,0
            h.append(taza)
    answer = max(answer, value)
print(answer)





