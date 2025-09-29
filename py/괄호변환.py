def devide(string):
    left = right = 0
    for i in range(len(string)):
        if string[i] == '(':
            left += 1
        else:
            right += 1
        if left == right:
            return string[:i + 1], string[i + 1:]


def check(u):
    stack = []
    for i in u:
        if i == '(':
            stack.append('1')
        else:
            if not stack:
                return False
            stack.pop()
    if stack:
        return False
    return True


def solution(p):
    if len(p) == 0:
        return ""

    u, v = devide(p)
    if check(u):
        return u + solution(v)
    else:
        answer = '('
        answer += solution(v)
        answer += ')'
        for n in u[1:-1]:
            if n == '(':
                answer += ')'
            else:
                answer += '('
        return answer
