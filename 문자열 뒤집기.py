o = list(map(int, input()))
count = 0
now = o[0]
o.pop(0)
for num in o:
    if num == now:
        continue;
    else:
        count+=1

print(count-1)