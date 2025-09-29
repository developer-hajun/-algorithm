def solution(n):
    cc = bin(n)[2:].count('1')
    for i in range(n+1,2000000):
        if bin(i)[2:].count('1')==cc:
            return i