while True:
    try:
        x = int(input())*10000000
        n = int(input())
        now = sorted(list(int(input()) for _ in range(n)))
        start,end = 0,n-1
        ch = True
        while start < end:
            value = now[start]+now[end]
            if x==value:
                ch=False
                break
            elif x<value:
                end-=1
            elif x>value:
                start+=1
        if ch:
            print("danger")
        else:
            print("yes "+str(now[start])+" "+str(now[end]))
    except:
        break