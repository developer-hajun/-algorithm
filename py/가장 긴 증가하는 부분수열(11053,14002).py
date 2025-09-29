a = int(input())

value = list(map(int,input().split()))
answer = [1]*a
save = []
for i in range(a):
    line=[value[i]]
    save.append(line)
mat =[]
count = 0
for i in range(1,a):
    for j in range(0,i):
        if value[i]>value[j]:
            if answer[i] < answer[j] + 1:
                answer[i]=answer[j] + 1
                save[i] = save[j]+[value[i]]
k = save[answer.index(max(answer))]
print(max(answer))
for i in k:
    print(i,end=' ')