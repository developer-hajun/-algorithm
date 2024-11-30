def change(n, q):
    rev_base = ''

    while n > 0:
        n, mod = divmod(n, q)
        rev_base += str(mod)
    return rev_base[::-1]

def is_prime(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n**0.5) + 1, 2):
        if n % i == 0:
            return False
    return True

def solution(n, k):
    answer = 0
    for i in change(n,k).split('0'):
        if len(i)!=0:
            if is_prime(int(i)):
                answer+=1
    return answer