a = int(input())

value = list(map(int,input().split()))
answer = [1]*a

for i in range(1,a):
    for j in range(0,i):
        if value[i]>value[j]:
            answer[i]=max(answer[i],answer[j]+1)
print(max(answer))