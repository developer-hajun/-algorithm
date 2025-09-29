o = int(input())
count = 0
for hour in range(o + 1):
    for min_value in range(60):
        for second in range(60):
            if '3' in str(hour)+str(min_value)+str(second):
                count+=1

print(count)
