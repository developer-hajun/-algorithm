from collections import deque


n = int(input())
value = list(map(int, input().split()))
stack = []
nums= 1
count = [0]*(n+1)

answer = [9999999] * (n+1)
for i in value:
    while stack and stack[-1][0]<=i:
        v,k = stack.pop()
    count[nums]+=len(stack)
    if stack:
        answer[nums] = stack[-1][1]
    stack.append([i,nums])
    nums+=1
value.reverse()
stack = []
nums=n
answer2 = [9999999]*(n+1)
for i in value:
    while stack and stack[-1][0]<=i:
        v,k = stack.pop()
    count[nums]+=len(stack)
    if stack:
        answer2[nums]= stack[-1][1]
    stack.append([i,nums])
    nums-=1
for i in range(1,n+1):
    now = count[i]
    if now==0:
        print(now)
        continue
    print(now,end=' ')
    a = abs(i-answer[i])
    b = abs(i-answer2[i])
    print( answer[i] if a<=b else answer2[i])