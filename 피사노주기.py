t = int(input())
for _ in range(t):
    a,b = map(int,input().split())
    mod1 = 0
    mod2 = 1
    count = 0
    while True:
        mod1,mod2 = mod2,(mod1+mod2)%b
        if mod1 == 0 and mod2 == 1:
            break
        count+=1

    print(a,count+1)