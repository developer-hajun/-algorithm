from collections import deque

dic = {'Y':2,'F':3,'O':4}
man = set()
n,game = input().split()
n = int(n)
count = 0
human = 1
for i in range(n):
    now= input()
    if now in man:
        continue
    man.add(now)
    human+=1
    if human%dic[game]==0:
        human=1
        count+=1
print(count)

