def timeToMinute(t):
    t = t.split(':')
    return int(t[0]) * 60 + int(t[1])


def solution(plans):
    for i in range(len(plans)):
        plans[i][1], plans[i][2] = timeToMinute(plans[i][1]), int(plans[i][2])
    plans.sort(key=lambda x: x[1])

    answer = []
    stack = []
    time = plans[0][1]

    for i in range(len(plans)):
        if i == len(plans) - 1:
            stack.append([plans[i][0], plans[i][2]])
            break
        now_subject, now_time, now_running = plans[i]
        next_subject, next_time, next_running = plans[i + 1]
        if time + now_running <= next_time:
            answer.append(now_subject)
            value = next_time - (time + now_running)
            while value != 0 and stack:
                sub, run = stack.pop()
                if run <= value:
                    answer.append(sub)
                    value -= run
                else:
                    stack.append([sub, run - value])
                    value = 0
        else:
            stack.append([now_subject, time + now_running - next_time])
        time = next_time

    while stack:
        answer.append(stack.pop()[0])
    return answer




