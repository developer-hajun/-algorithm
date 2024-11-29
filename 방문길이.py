def solution(dirs):
    route = []
    move = {'L':[0,-1],'R':[0,1],'U':[-1,0],'D':[1,0]}
    y,x = 0,0
    for i in dirs.strip():
        move_y,move_x = move[i]
        ny,nx = y+move_y,x+move_x
        if -5<=ny<=5 and -5<=nx<=5:
            ans = [(ny,nx),(y,x)]
            ans.sort()
            route.append(ans)
            y,x = ny,nx
    answer = set()
    for i in route:
        answer.add((i[0],i[1]))
    return len(answer)