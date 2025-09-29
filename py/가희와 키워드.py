import sys

input = sys.stdin.readline
n,m = map(int,input().split())

dan = set()
for i in range(n):
    dan.add(input().strip())

for _ in range(m):
    dan -= set(input().strip().split(','))
    print(len(dan))