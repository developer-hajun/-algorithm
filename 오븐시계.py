time,mini = map(int,input().split())

k = int(input())
mini+=k

time += mini//60
mini = mini%60
time = time%24

print(time,mini)