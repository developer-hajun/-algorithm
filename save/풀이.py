n = int(input())

now = [0,1,1,1,1,1,1,1,1,1]
for i in range(1,n):
    new_arr=[]
    for j in range(10):
        value = 0
        if 0<=j-1:
            value+=now[j-1]
        if j+1<10:
            value+=now[j+1]
        new_arr.append(value%1000000000)
    now=new_arr
print(sum(now)%1000000000)