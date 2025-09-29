from itertools import permutations
from collections import deque
import re, copy


def operation(num1, num2, op):
    if op == '+':
        return str(int(num1) + int(num2))
    if op == '-':
        return str(int(num1) - int(num2))
    if op == '*':
        return str(int(num1) * int(num2))


def solution(expression):
    tokens = re.findall(r'\d+|[+*-]', expression)
    answer = -1
    for priority in permutations(['+', '-', '*'], 3):
        token = copy.deepcopy(tokens)
        for o in priority:
            stack = []
            while len(token) != 0:
                tmp = token.pop(0)
                if tmp == o:
                    stack.append(operation(stack.pop(), token.pop(0), o))
                else:
                    stack.append(tmp)
            token = stack
        answer = max(answer, abs(int(token[0])))
    return answer



