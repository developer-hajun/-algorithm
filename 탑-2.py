n = int(input())
value = []
st=1
for i in list(map(int,input().split())):
    value.append([i,st])
    st+=1
stack=[]
answer = []
for num,x in value:
    while stack and stack[-1][0]<num:
        stack.pop()
    if not stack:
        answer.append(0)
    else:
        answer.append(stack[-1][1])
    stack.append([num,x])
for i in answer:
    print(i,end=" ")