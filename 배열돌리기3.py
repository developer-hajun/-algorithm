def rotate1():
    implement_matrix = []
    while matrix:
        implement_matrix.append(matrix.pop())
    return implement_matrix
def rotate2():
    implement_matrix = []
    for i in matrix:
        i.reverse()
        implement_matrix.append(i)
    return implement_matrix
def rotate3():
    return [list(row)[::-1] for row in zip(*matrix)]
def rotate4():
    return [list(row) for row in zip(*matrix)][::-1]
def rotate5():
    implement_matrix  = []
    a=[]
    b=[]
    for i in range(len(matrix)//2):
        a.append(matrix[i][:len(matrix[i])//2])
        b.append(matrix[i][len(matrix[i])//2:])
    c=[]
    d=[]
    for i in range(len(matrix)//2,len(matrix)):
        c.append(matrix[i][:len(matrix[i])//2])
        d.append(matrix[i][len(matrix[i])//2:])
    for i in range(len(matrix)//2):
        implement_matrix.append(c[i]+a[i])
    now=0
    for i in range(len(matrix)//2,len(matrix)):
        implement_matrix.append(d[now]+b[now])
        now+=1
    return implement_matrix

def rotate6():
    implement_matrix  = []
    a=[]
    b=[]
    for i in range(len(matrix)//2):
        a.append(matrix[i][:len(matrix[i])//2])
        b.append(matrix[i][len(matrix[i])//2:])
    c=[]
    d=[]
    for i in range(len(matrix)//2,len(matrix)):
        c.append(matrix[i][:len(matrix[i])//2])
        d.append(matrix[i][len(matrix[i])//2:])
    for i in range(len(matrix)//2):
        implement_matrix.append(b[i]+d[i])
    now = 0
    for i in range(len(matrix) // 2, len(matrix)):
        implement_matrix.append(a[now] + c[now])
        now += 1
    return implement_matrix

n, m, r = list(map(int, input().split()))
matrix = [list(map(int, input().split())) for i in range(n)]
#rotate1()
#rotate2()
#rotate3()
#rotate4()
#rotate5()
#rotate6()
line = list(map(int, input().split()))
for case in line:
    if case==1:
        matrix = rotate1()
    elif case==2:
        matrix = rotate2()
    elif case==3:
        matrix = rotate3()
    elif case==4:
        matrix = rotate4()
    elif case==5:
        matrix = rotate5()
    elif case==6:
        matrix = rotate6()
for _ in matrix:
    for j in _:
        print(j,end=' ')
    print()