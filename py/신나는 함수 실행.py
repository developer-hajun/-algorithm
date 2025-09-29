while(True):
    answer = [[[0] * 21 for _ in range(21)] for _ in range(21)]
    for i in range(21):
        for j in range(21):
            for k in range(21):
                if i==0 or j==0 or k==0:
                    answer[i][j][k]=1
                elif i<j<k:
                    answer[i][j][k]=answer[i][j][k-1]+answer[i][j-1][k-1]-answer[i][j-1][k]
                else:
                    answer[i][j][k]=answer[i-1][j][k]+answer[i-1][j-1][k]+answer[i-1][j][k-1]-answer[i-1][j-1][k-1]
    a, b, c = map(int, input().split())
    new_a,new_b,new_c = a,b,c
    if a==b==c==-1:
        break
    elif a<=0 or b<=0 or c<=0:
        print(f"w({a}, {b}, {c}) = {1}")
        continue
    elif a > 20 or b > 20 or c > 20:
        new_a = 20
        new_b = 20
        new_c = 20
    print(f"w({a}, {b}, {c}) = {answer[new_a][new_b][new_c]}")