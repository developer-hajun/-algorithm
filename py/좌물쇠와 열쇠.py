from collections import deque
import copy


def solution(key, lock):
    count = 0
    for _ in lock:
        count += _.count(0)

    def rotate(matrix):
        return [list(row) for row in zip(*matrix[::-1])]

    lock_len = len(lock)
    lock = [[0] * (lock_len * 3) for _ in range(lock_len)] + lock + [[0] * (lock_len * 3) for _ in range(lock_len)]
    for _ in range(lock_len, lock_len * 2):
        lock[_] = [0] * lock_len + lock[_] + [0] * lock_len
    for _ in lock:
        print(_)
    key_start = len(key) // 2
    key_end = 0
    if len(key) % 2 == 0:
        key_end = len(key) // 2
    else:
        key_end = len(key) // 2 + 1

    keys = []
    keys.append(key)
    for i in range(3):
        key = rotate(key)
        keys.append(key)

    def check(y, x, ins):
        k = 0
        for ny in range(y - key_start, y + key_end):
            for nx in range(x - key_start, x + key_end):
                if lock_len <= ny < lock_len * 2 and lock_len <= nx < lock_len * 2:
                    if ins[ny - (y - key_start)][nx - (x - key_start)] == 1:
                        if lock[ny][nx] == 1:
                            return False
                        else:
                            k += 1
                    elif lock[ny][nx] == 0:
                        return False
        if k == count:
            return True
        else:
            return False

    for y in range(len(lock)):
        for x in range(len(lock)):
            for key in keys:
                if check(y, x, key):
                    return True
    return False


