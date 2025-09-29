import java.util.*;

class UserSolution {
    static class robot {
        int id;
        int score; 
        int beginTime;
        int status; 

        public robot(int i) {
            id = i;
            score = 0;
            beginTime = 0;
            status = 0;
        }
    }
    
    static TreeSet<robot> minSet;
    static TreeSet<robot> maxSet;

    static robot[] robots;
    static HashMap<Integer, robot[]> jobs;

    public void init(int N) {

        minSet = new TreeSet<>((o1, o2) -> {
            if (o1.score != o2.score) return o1.score - o2.score;
            return o1.id - o2.id;
        });
        maxSet = new TreeSet<>((o1, o2) -> {
            if (o1.score != o2.score) return o2.score - o1.score;
            return o1.id - o2.id;
        });
        
        jobs = new HashMap<>();
        robots = new robot[N + 1];
        for (int i = 1; i <= N; i++) {
            robot rob = new robot(i);
            robots[i] = rob;
            minSet.add(rob);
            maxSet.add(rob);
        }
    }

    public int callJob(int cTime, int wID, int mNum, int mOpt) {
        ArrayList<robot> assignedRobots = new ArrayList<>();
        int ans = 0;

        for (int i = 0; i < mNum; i++) {
            robot now = (mOpt == 0) ? maxSet.pollFirst() : minSet.pollFirst();
            if (now == null) break;

            if(mOpt == 0) minSet.remove(now);
            else maxSet.remove(now);

            now.status = -wID;
            now.beginTime = cTime;
            ans += now.id;
            assignedRobots.add(now);
        }

        jobs.put(wID, assignedRobots.toArray(new robot[0]));
        return ans;
    }

    public void returnJob(int cTime, int wID) {
        if (!jobs.containsKey(wID)) return;
        
        for (robot now : jobs.get(wID)) {
            if (now.status != -wID) continue;

            now.score -= (cTime - now.beginTime);
            now.status = 0;
            
            minSet.add(now);
            maxSet.add(now);
        }
        jobs.remove(wID);
    }



	 public void broken(int cTime, int rID) {
	     robot now = robots[rID];
	
	 
	     if (now.status < 0) {
	  
	         now.status = Integer.MAX_VALUE;
	     }
	 }

    public void repair(int cTime, int rID) {
        robot now = robots[rID];
        if (now.status != Integer.MAX_VALUE) return;

        now.score = -cTime;
        now.status = 0;
        
        minSet.add(now);
        maxSet.add(now);
    }

    public int check(int cTime, int rID) {
        robot now = robots[rID];
        if (now.status == Integer.MAX_VALUE) {
            return 0;
        } else if (now.status < 0) {
            return now.status;
        } else {
            return now.score + cTime;
        }
    }
}
