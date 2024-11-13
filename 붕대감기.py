def solution(bandage, health, attacks):
    time = 0
    max_health = health

    for attack_time, damage in attacks:
        count = 0
        while attack_time > time:
            time += 1
            health += bandage[1]
            count += 1
            if count == bandage[0]:
                health += bandage[2]
                count = 0
        if health > max_health:
            health = max_health

        health -= damage
        time += 1
        if health <= 0:
            return -1
    return health