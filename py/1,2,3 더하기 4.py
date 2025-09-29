from collections import deque

n = int(input())
line = [int(input()) for _ in range(n)]
answer = [1]*(max(line)+1)

for i in range(2,max(line)+1):
    answer[i]+=answer[i-2]
for i in range(3,max(line)+1):
    answer[i]+=answer[i-3]

for i in line:
    print(answer[i])