def xor_array(arr):
    result = 0
    for num in arr:
        result ^= num  # XOR 연산
    return result


def solution(data, col, row_begin, row_end):
    data.sort(key=lambda x: (x[col - 1], -x[0]))
    data = data[row_begin - 1: row_end]
    now = 0

    value = []
    for i in range(row_begin, row_end + 1):
        v = 0
        for j in data[now]:
            v += j % i
        now += 1
        value.append(v)

    xor_result = xor_array(value)
    return xor_result