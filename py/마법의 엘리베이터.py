def solution(storey):
    answer = 0
    st = storey
    while st != 0:
        value = st % 10
        if value >= 6:
            answer += 10 - value
            st += 10 - value
        elif value == 5 and int(str(st // 10)[-1]) >= 5:
            st += 10 - value
            answer += 10 - value
        else:
            answer += value

        st //= 10
    return answer
