from itertools import combinations, permutations


def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False

    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6

    return True


def solution(numbers):
    nums = list(numbers.strip())
    save = set()
    count = 0
    for i in range(1, len(nums) + 1):
        now_nums = set(permutations(nums, i))
        for num in now_nums:
            value = int(''.join(num))
            save.add(int(value))
    for i in save:
        if is_prime(i):
            count += 1

    return count
