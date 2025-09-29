t = int(input())

value = list(int(input()) for _ in range(t))
fibo = [[0,0] for _ in range(41)]
fibo[0]=[1,0]
fibo[1]=[0,1]
for i in range(2,max(value)+1):
    fibo[i][0]=fibo[i-1][0]+fibo[i-2][0]
    fibo[i][1]=fibo[i-2][1]+fibo[i-1][1]
for _ in value:
    print(fibo[_][0],fibo[_][1])