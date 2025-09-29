from collections import deque
def solution(new_id):
    new_id = new_id.lower()
    new_str = ""
    for i in range(len(new_id)):
        if 97<=ord(new_id[i])<=122 or 48<=ord(new_id[i])<=57:
            new_str+=new_id[i]
        elif new_id[i] in ['-','_','.']:
            new_str+=new_id[i]
    new_id = new_str
    queue = deque(list(new_id.strip()))
    new_str=""
    while queue:
        if len(queue)<=1:
            new_str+=queue.popleft()
        else:
            while len(queue)>=2 and queue[0]==queue[1]=='.':
                queue.popleft()
                queue.popleft()
                queue.appendleft('.')
            new_str+=queue.popleft()
    now = deque(list(new_str.strip()))
    if len(now)==1:
        if now[0]=='.':
            now.popleft()
    elif len(now)>=2:
        if now[0]=='.':
            now.popleft()
        if now[-1]=='.':
            now.pop()
    if len(now)==0:
        now="a"
    now = list(now)
    if len(now)>=16:
        now = list(now)[:15]
        if now[-1]=='.':
            now.pop()
    while len(now)<3:
        now.append(now[-1])
    return ''.join(now)
