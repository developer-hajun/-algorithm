n = int(input())
value = list(map(int, input().split()))
start=0
end =0
answer = 0

visit = [False]*100001

while start <= end < n:
    if not visit[value[end]]:
        visit[value[end]]=True
        end+=1
        answer += end-start
    else:
        while visit[value[end]]:
            visit[value[start]]=False
            start+=1
print(answer)