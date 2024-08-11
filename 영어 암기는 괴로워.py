n,m = map(int,input().split())
arr = {}
for _ in range(n):
  now = input()
  if len(now)<m:
    continue
  else:
    if now not in arr:
      arr[now]=1
    else:
      arr[now]+=1
answer =[]
for i in arr:
  answer.append([i,arr[i]])

answer.sort(key=lambda x:(-x[1],-len(x[0]),x[0]))
for i in answer:
  print(i[0])