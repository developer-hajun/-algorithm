n = list(map(int,input()))
count = 0
now = n[0]
n.pop(0)
for num in n:
    if num == now:
        continue;
    else:
        count+=1

print(count-1)