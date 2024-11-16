def solution(friends, gifts):
    friend = {}
    count = 0
    for i in friends:
        friend[i] = count
        count += 1

    arr = [[0] * len(friends) for _ in range(len(friends))]
    for i in gifts:
        a, b = i.split()
        arr[friend[a]][friend[b]] += 1
    gi = [0] * len(friends)
    for i in range(len(friends)):
        gi[i] += sum(arr[i])
        for j in range(len(friends)):
            gi[i] -= arr[j][i]
    answer = [0] * len(friends)

    for i in range(len(friends)):
        for j in range(len(friends)):
            if i == j:
                continue
            if arr[i][j] > arr[j][i]:
                answer[i] += 1
            elif arr[i][j] == arr[j][i]:
                if gi[i] > gi[j]:
                    answer[i] += 1
    return max(answer)



