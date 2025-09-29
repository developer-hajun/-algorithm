import math
from functools import reduce


def gcd_multiple(numbers):
    return reduce(math.gcd, numbers)


def find_divisors(n):
    divisors = []
    for i in range(1, int(n ** 0.5) + 1):
        if n % i == 0:
            divisors.append(i)
            if i != n // i:  # i와 n // i가 다를 경우
                divisors.append(n // i)
    return sorted(divisors)[1:]


def common_divisors(numbers):
    gcd_value = gcd_multiple(numbers)  # 모든 수의 최소공약수
    return find_divisors(gcd_value)


def solution(arrayA, arrayB):
    answer = 0
    now = common_divisors(arrayA)
    while now:
        value = now.pop()
        ch = True
        for i in arrayB:
            if i % value == 0:
                ch = False
                break
        if ch:
            answer = value
            break
    now = common_divisors(arrayB)
    while now:
        value = now.pop()
        if value < answer:
            break
        ch = True
        for i in arrayA:
            if i % value == 0:
                ch = False
                break
        if ch:
            answer = value
            break
    return answer
