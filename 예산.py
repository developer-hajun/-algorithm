import sys

n = int(input())
request = list(map(int, input().split()))
money = int(input())

check = money
for i in request:
    check -= i
if check > -1:
    print(max(request))
    sys.exit()

st =0
end=max(request)
answer = 0
while st <= end:
  check = money
  mid = (st+end)//2
  for i in request:
    check -= min(mid,i)
  if check>-1:
    st=mid+1
    answer = max(mid,answer)
  else:
    end=mid-1
print(answer)
