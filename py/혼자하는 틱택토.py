
def find(value):
    if arr[0][0] == arr[1][1] == arr[2][2] == value:
        return True
    if arr[0][2] == arr[1][1] == arr[2][0] == value:
        return True
    if arr[0][0] == arr[0][1] == arr[0][2] == value:
        return True
    if arr[1][0] == arr[1][1] == arr[1][2] == value:
        return True
    if arr[2][0] == arr[2][1] == arr[2][2] == value:
        return True
    if arr[0][0] == arr[1][0] == arr[2][0] == value:
        return True
    if arr[0][1] == arr[1][1] == arr[2][1] == value:
        return True
    if arr[0][2] == arr[1][2] == arr[2][2] == value:
        return True
    return False


arr = []


def solution(string):
    arr.append(list(string[0].strip()))
    arr.append(list(string[1].strip()))
    arr.append(list(string[2].strip()))

    X = 0
    O = 0
    for i in range(3):
        for j in range(3):
            if arr[i][j] == 'X':
                X += 1
            elif arr[i][j] == 'O':
                O += 1

    if O == X and not find('O'):
        return 1
    if O == X + 1 and not find('X'):
        return 1
    return 0
