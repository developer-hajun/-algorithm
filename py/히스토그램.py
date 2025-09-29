from collections import deque

dic = {}
dic["+"] =1
dic["-"] =1
dic["/"] =2
dic["*"] =2
dic["("]=0
dic[")"]=0
stack = []

ins = input()

for i in ins:
    print(stack)
    if i=="(":
        stack.append(i)
    elif i==")":
        while stack[-1]!="(":
            print(stack.pop(),end="")
        stack.pop()
    elif i not in dic:
        print(i,end="")
    else:
        if len(stack)==0:
            stack.append(i)
        else:
            if dic[stack[-1]] >= dic[i]:
                while len(stack)!=0 and dic[stack[-1]]>=dic[i]:
                    print(stack.pop(),end="")
                stack.append(i)
            else:
                stack.append(i)

while stack:
    print(stack.pop(),end="")