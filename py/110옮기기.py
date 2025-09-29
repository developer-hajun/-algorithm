def find_110(now):
    stack = []
    count = 0
    for value in now:
        if value == '0' and stack[-2:] == ["1", "1"]:
            stack.pop()
            stack.pop()
            count += 1
        else:
            stack.append(value)
    return ''.join(stack), count


def plus_110(now, cnt):
    for i in range(len(now) - 1, -1, -1):
        if now[i] == "0":
            return now[:i + 1] + "110" * cnt + now[i + 1:]
    return "110" * cnt + now


def solution(s):
    answer = []
    for value in s:
        now, count = find_110(value)
        now = plus_110(now, count)
        answer.append(now)
    return answer