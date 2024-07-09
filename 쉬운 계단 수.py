import math
n=int(input())
line= []
for i in range(n+1):
    line.append([0,0,0,0,0,0,0,0,0,0])
line[1] = [0,1,1,1,1,1,1,1,1,1]
mod = 1000000000
for i in range(2,n+1):
    line[i][0] = (line[i-1][1])%mod
    line[i][1] = (line[i - 1][2] + line[i - 1][0])%mod
    line[i][2] = (line[i - 1][1] + line[i - 1][3])%mod
    line[i][3] = (line[i - 1][2] + line[i - 1][4])%mod
    line[i][4] = (line[i - 1][3] + line[i - 1][5])%mod
    line[i][5] = (line[i - 1][4] + line[i - 1][6])%mod
    line[i][6] = (line[i - 1][5] + line[i - 1][7])%mod
    line[i][7] = (line[i - 1][6] + line[i - 1][8])%mod
    line[i][8] = (line[i - 1][7] + line[i - 1][9])%mod
    line[i][9] = (line[i - 1][8])%mod
print(sum(line[n])%mod)
