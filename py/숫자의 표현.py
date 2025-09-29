def solution(n):
    ns = [i for i in range(1, n + 1)]

    left = 0
    right = 0
    value = 0
    count = 0

    while right < n:
        while right < n and value < n:
            value += ns[right]
            right += 1
        while left < n and value >= n:
            if value == n:
                count += 1
            value -= ns[left]
            left += 1
    return count
