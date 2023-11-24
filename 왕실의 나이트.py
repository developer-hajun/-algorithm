n = input()
x = ord(n[0])-ord('a') +1
y = int(n[1])

#y,x
moves = [[-2,-1],[-2,1],[2,-1],[2,1],[-1,-2],[-1,2],[1,-2],[1,2]]
count=0
for move in moves:
    dy = y+move[0]
    dx = x+move[1]
    if dx<1 or dx>8 or dy<1 or dx>8:
        continue
    count+=1
print(count)
