job = [[7,8,"t1"],[3,7,"t2"],[1,5,"t3"],[5,9,"t4"],[0,2,"t5"],[6,8,"t6"],[1,6,"t7"]]
job.sort(key = lambda x:[x[0],-x[1]])
save = []
machine = []
ends = 0
for start,end,t in job:
    ends = max(end,ends)
    if not machine:
        save.append([0,start, end, t])
        machine.append(end)
    else:
        ch = True
        for i in range(len(machine)):
            if machine[i]<=start:
                ch = False
                machine[i]=end
                save.append([i,start, end, t])
                break
        if ch:
            save.append([len(machine), start, end, t])
            machine.append(end)
f = [['machine '+str(_+1)]+['  ']*ends for _ in range(len(machine))]
for num,start,end,t in save:
    for i in range(start+1,end+1):
        f[num][i] = t
f.reverse()
f = [["time     ","0 ","1 ","2 ","3 ","4 ","5 ","6 ","7 ","8 "]]+f
for _ in f:
    for k in _:
        print(k,end=' ')
    print()



