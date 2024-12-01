def solution(n, works):
    if sum(works) <= n:
        return 0
    max_value = sorted(list(set(works)))
    works.sort(reverse=True)
    # 최대한 내리기 -> 이전값과 같을때 까지 각각 -1
    while n != 0:
        left = max_value.pop()  # 최대값
        if not max_value:
            right = 0
        else:
            right = max_value[-1]  # 내려야할 값
        stack = []
        for i in range(len(works)):
            if left == works[i]:
                stack.append(i)
        # 최대값을 가지는 것들을 가져옴
        while n != 0 and left != right:
            for i in stack:
                works[i] -= 1
                n -= 1
                if n == 0:
                    break
            left -= 1
    for i in range(len(works)):
        works[i] **= 2
    return sum(works)



