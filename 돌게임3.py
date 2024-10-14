import sys

n = int(input())

game = [0]*1001

# 1 상근 0 창영

game[1]=1
game[2]=0
game[3]=1
game[4]=1
for i in range(5,n+1):
    if game[i-4]==0 or game[i-1]==0 or game[i-3]==0:
        game[i]=1
    else:
        game[i]=0

if game[n]==1:
    print("SK")
else:
    print("CY")