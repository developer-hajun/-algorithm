o = map(int, input())
data = list(map(int,input().split()))
data.sort()

count = 0;

team = []
for go in data:
    team.append(go)
    if max(team) == len(team):
        count+=1
        team=[]
    else:
        continue
print(count)
