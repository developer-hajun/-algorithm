
class UserSolution {

    static class Human {
        int id;
        int version;
        Human next;

        public Human(int id, int version, Human next) {
            this.id = id;
            this.version = version;
            this.next = next;
        }

        public Human() {
            this.id = -1;
            this.version = -1;
            this.next = null;
        }
    }

    static class Team {
        Human[] head = new Human[6];
        Human[] tail = new Human[6]; 
    }

    static final int MAX_SOLDIERS = 100001;
    static Team[] teams = new Team[6];
    static int[] soldierVersion = new int[MAX_SOLDIERS];
    public int[] soldierTeam = new int[MAX_SOLDIERS];

    public void init() {
        for (int i = 1; i <= 5; i++) {
            teams[i] = new Team();
            for (int j = 1; j <= 5; j++) {
                teams[i].head[j] = new Human();
                teams[i].tail[j] = teams[i].head[j];
            }
        }
    }

    public void hire(int mID, int mTeam, int mScore) {
        int newVersion = ++soldierVersion[mID];
        Human newSoldier = new Human(mID, newVersion, null);

        teams[mTeam].tail[mScore].next = newSoldier;
        teams[mTeam].tail[mScore] = newSoldier;

        soldierTeam[mID] = mTeam;
    }

    public void fire(int mID) {
        soldierVersion[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore) {
        hire(mID, soldierTeam[mID], mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0) {
            return;
        }

        if (mChangeScore > 0) {
            for (int k = 4; k >= 1; k--) {
                int next_score = Math.min(k + mChangeScore, 5);

                if (k == next_score) continue;

                if (teams[mTeam].head[k].next == null) continue;


                teams[mTeam].tail[next_score].next = teams[mTeam].head[k].next;
                teams[mTeam].tail[next_score] = teams[mTeam].tail[k];


                teams[mTeam].head[k].next = null;
                teams[mTeam].tail[k] = teams[mTeam].head[k];
            }
        } else { 

            for (int k = 2; k <= 5; k++) {
                int next_score = Math.max(k + mChangeScore, 1);


                if (k == next_score) continue;

                if (teams[mTeam].head[k].next == null) continue;

                teams[mTeam].tail[next_score].next = teams[mTeam].head[k].next;
                teams[mTeam].tail[next_score] = teams[mTeam].tail[k];

                teams[mTeam].head[k].next = null;
                teams[mTeam].tail[k] = teams[mTeam].head[k];
            }
        }
    }

    public int bestSoldier(int mTeam) {
        for (int j = 5; j >= 1; j--) {
            Human node = teams[mTeam].head[j].next;
            if (node == null) continue;

            int bestID = 0;
            while (node != null) {
                if (node.version == soldierVersion[node.id]) {
                    if (node.id > bestID) {
                        bestID = node.id;
                    }
                }
                node = node.next;
            }

            if (bestID != 0) {
                return bestID;
            }
        }
        
        return 0;
    }
}
