
n = int(input())
water = list(map(int,input().split()))

answer = []
count = 2000000002

st,en = 0,n-1

while st<en:
    value = water[st]+water[en]
    if abs(value)<count:
        count = abs(value)
        answer=[water[st],water[en]]
        if abs(value)==0:
            break
    if value<0:
        st+=1
    else:
        en-=1
print(str(answer[0])+" "+str(answer[1]))