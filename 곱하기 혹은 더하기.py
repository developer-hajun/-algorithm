n = list(map(int,input()))
start_value = n[0]
n.pop(0)
for num in n:
    x = start_value*num
    plus = start_value+num
    if x > plus:
        start_value = x;
    if x<= plus:
        start_value = plus
print(start_value)
