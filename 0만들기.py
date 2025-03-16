from itertools import product

t = int(input())

for _ in range(t):
    num = int(input())
    arr = ['+','-','']
    answer = []
    for i in product(arr,repeat = num-1):
        su = "1"
        now = "1"
        value=2
        for j in i:
            su+=(j+str(value))
            if j=='':
                j=" "
            now+=(j+str(value))
            value+=1
        if eval(su)==0:
            answer.append(now)
    answer.sort()
    for _ in answer:
        print(_)
    print()