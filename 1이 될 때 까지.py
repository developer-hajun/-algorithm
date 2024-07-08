o,k = map(int, input().split())
count = 0
while o!=1:
    if o%k==0: o /= 5
    else: o-=1
    count+=1
# O(N)
print(count)
o,k = map(int, input().split())
count = 0
while o>=k:
    registar = (o / k) * k
    count += (o - registar)
    o = registar
    if o<k:
        break
    o = o / k
    count+=1
count += (o - 1)
print(int(count))


