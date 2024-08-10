n, m = map(int, input().split())
a = list(input())

for i in range(n):
    a[i] = int(a[i])
answer = []
count = 0
for i in a:
    while len(answer)>0 and count<m and answer[-1]<i:
        answer.pop()
        count+=1
    answer.append(i)

while len(answer)>(n-m):
    answer.pop()
print(''.join(map(str, answer)))