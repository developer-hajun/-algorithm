r, c, k = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(3)]
r -= 1
c -= 1

def R():
    global matrix
    new_matrix = []
    max_len = 0
    for row in matrix:
        counter = {}
        for num in row:
            if num == 0:
                continue
            counter[num] = counter.get(num, 0) + 1
        sorted_row = []
        for key, val in sorted(counter.items(), key=lambda x: (x[1], x[0])):
            sorted_row.extend([key, val])
        max_len = max(max_len, len(sorted_row))
        new_matrix.append(sorted_row)

    # 모든 행을 max_len만큼 0으로 채우기
    for i in range(len(new_matrix)):
        new_matrix[i].extend([0] * (max_len - len(new_matrix[i])))
        if len(new_matrix[i]) > 100:
            new_matrix[i] = new_matrix[i][:100]

    return new_matrix

def C():
    global matrix
    transposed = list(zip(*matrix))
    new_matrix = []
    max_len = 0
    for col in transposed:
        counter = {}
        for num in col:
            if num == 0:
                continue
            counter[num] = counter.get(num, 0) + 1
        sorted_col = []
        for key, val in sorted(counter.items(), key=lambda x: (x[1], x[0])):
            sorted_col.extend([key, val])
        max_len = max(max_len, len(sorted_col))
        new_matrix.append(sorted_col)

    # 0으로 채우기
    for i in range(len(new_matrix)):
        new_matrix[i].extend([0] * (max_len - len(new_matrix[i])))
        if len(new_matrix[i]) > 100:
            new_matrix[i] = new_matrix[i][:100]

    # 다시 전치
    result = list(zip(*new_matrix))
    result = [list(row) for row in result]
    return result

for t in range(101):
    if 0 <= r < len(matrix) and 0 <= c < len(matrix[0]) and matrix[r][c] == k:
        print(t)
        break
    if len(matrix) >= len(matrix[0]):
        matrix = R()
    else:
        matrix = C()
else:
    print(-1)
