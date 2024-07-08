o = list(map(int, input()))
start_value = o[0]
o.pop(0)
for num in o:
    x = start_value*num
    plus = start_value+num
    if x > plus:
        start_value = x;
    if x<= plus:
        start_value = plus
print(start_value)
