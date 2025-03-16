n,s = map(int,input().split())
nums = list(map(int,input().split()))
st=0
value=0
answer =[]
for i in range(n):
    while value>=s and st<i:
        value-=nums[st]
        st+=1
        if not answer:
            answer=[st,i]
        elif i-st<answer[1]-answer[0]:
            answer=[st,i]
    value+=nums[i]
while value>=s and st<n:
    value -= nums[st]
    st += 1
    if not answer:
        answer = [st, n]
    elif n - st < answer[1] - answer[0]:
        answer = [st, n]
if not answer:
    print(0)
else:
    print(answer[1]-answer[0]+1)