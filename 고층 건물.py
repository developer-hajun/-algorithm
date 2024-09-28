n = int(input())
value = list(map(int, input().split()))

answer = -1
cnt = [0]*n
for i in range(n):
    level = -999999999999
    for j in range(i+1,n):
        now_level = (value[j] - value[i]) / abs(j - i)
        if now_level > level:
            level = now_level
            cnt[i]+=1
            cnt[j]+=1
print(max(cnt))