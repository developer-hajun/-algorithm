from collections import deque


def time_to_int(time):
    a, b, c = time.split(':')
    return int(a) * 60 * 60 + int(b) * 60 + int(c)


def int_to_time(time):
    hour, minute, sec = time // 60 // 60, time // 60 % 60, time % 60
    if len(str(hour)) == 1:
        hour = "0" + str(hour)
    if len(str(minute)) == 1:
        minute = "0" + str(minute)
    if len(str(sec)) == 1:
        sec = "0" + str(sec)
    return str(hour) + ":" + str(minute) + ":" + str(sec)


def solution(play_time, adv_time, logs):
    play_time = time_to_int(play_time)
    adv_time = time_to_int(adv_time)
    total = [0 for i in range(play_time + 1)]

    for i in range(len(logs)):
        start, end = logs[i].split('-')
        total[time_to_int(start)] += 1
        total[time_to_int(end)] -= 1

    for i in range(1, play_time):
        total[i] += total[i - 1]
    for i in range(1, play_time):
        total[i] += total[i - 1]

    answer = 0
    max_total = total[adv_time - 1]
    for current_time in range(0, play_time - adv_time):
        if total[current_time + adv_time] - total[current_time] > max_total:
            max_total = total[current_time + adv_time] - total[current_time]
            answer = current_time + 1
    return int_to_time(answer)
