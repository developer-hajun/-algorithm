n = int(input())
line = list(map(int, input().split()))

t = int(input())
for _ in range(t):
  command,num = map(int,input().split())
  start = num-1
  if command==1:
    for i in range(start,n,num):
      line[i] = 1 if line[i]==0 else 0
  else:
    st = start-1
    end = start+1
    while 0<=st and end<n and line[st]==line[end]:
      if line[st]==1:
        line[st]=0
      else:
        line[st]=1
      if line[end]==1:
        line[end]=0
      else:
        line[end]=1
      st-=1
      end+=1
    line[start] = 1 if line[start]==0 else 0
count=0
for _ in line:
  print(_,end=' ')
  count+=1
  if count==20:
    count=0
    print()