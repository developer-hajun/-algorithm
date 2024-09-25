def find(value):
    if arr[0][0]==arr[1][1]==arr[2][2]==value:
        return True
    if arr[0][2]==arr[1][1]==arr[2][0]==value:
        return True
    if arr[0][0]==arr[0][1]==arr[0][2]==value:
        return True
    if arr[1][0]==arr[1][1]==arr[1][2]==value:
        return True
    if arr[2][0]==arr[2][1]==arr[2][2]==value:
        return True
    if arr[0][0]==arr[1][0]==arr[2][0]==value:
        return True
    if arr[0][1]==arr[1][1]==arr[2][1]==value:
        return True
    if arr[0][2]==arr[1][2]==arr[2][2]==value:
        return True
    return False


while True:
    string = input()
    if string == "end":
        break

    arr =[]
    arr.append(list(string[:3].strip()))
    arr.append(list(string[3:6].strip()))
    arr.append(list(string[6:].strip()))

    X = 0
    O = 0
    for i in range(3):
        for j in range(3):
            if arr[i][j]=='X':
                X+=1
            elif arr[i][j]=='O':
                O+=1
    if X>O+1:
        print("invalid")
        continue
    if O > X:
        print("invalid")
        continue
    if X == O+1 and find('X') and not find('O'):
        print("valid")
        continue
    if O==X and find('O') and not find('X'):
        print("valid")
        continue
    if X == 5 and O==4 and not find('O'):
        print("valid")
        continue
    print("invalid")