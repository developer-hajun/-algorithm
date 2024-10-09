import sys

n = int(input())
answer = 1
if n<=1:
    print(answer)
    sys.exit()
for i in range(2,n+1):
    answer *= i
print(answer)