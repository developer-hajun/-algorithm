def solution(skill, skill_trees):
    pp = {v:idx for idx,v in enumerate(skill.strip())}
    answer = 0
    for i in skill_trees:
        check=True
        now = 0
        for j in i.strip():
            if j not in skill:
                continue
            else:
                if j==skill[now]:
                    now+=1
                else:
                    check=False
                    break
        if check:
            answer+=1
    return answer