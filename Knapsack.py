value = [[0,0],[5,10],[4,40],[6,30],[3,50]]

arr =[[0]*11 for _ in range(5)]

for i in range(1,5):
    for j in range(1,11):
        if value[i][0]>j:
            arr[i][j] = arr[i-1][j]
            continue
        arr[i][j] = max(arr[i-1][j],arr[i-1][j-value[i][0]]+value[i][1])