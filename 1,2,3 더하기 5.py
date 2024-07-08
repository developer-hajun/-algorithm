n=int(input())
value = list(int(input()) for _ in range(n))
m = max(value)
card = []
for i in range(m+1):
    card.append([0,0,0])
card[1] = [1,0,0]
card[2] = [0,1,0]
card[3] = [1,1,1]

for i in range(4,m+1):
    card[i][0] = (card[i-1][1]+card[i-1][2])%1000000009
    card[i][1] = (card[i-2][0]+card[i-2][2])%1000000009
    card[i][2] = (card[i-3][0]+card[i-3][1])%1000000009

for i in value:
    print(sum(card[i])%1000000009)

