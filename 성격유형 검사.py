def solution(survey, choices):
    dic = {"R": 0, "T": 0, "C": 0, "F": 0, "J": 0, "M": 0, "A": 0, "N": 0}

    for i in range(len(survey)):
        surF, surS = survey[i].strip()
        choice = choices[i]
        if choice > 4:
            dic[surS] += choice - 4
        elif choice < 4:
            dic[surF] += 4 - choice
    answer = ""

    for k in ["RT", "CF", "JM", "AN"]:
        surF, surS = k.strip()

        if dic[surF] >= dic[surS]:
            answer += surF
        else:
            answer += surS
    return answer