import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class UserSolution {

    // Human 클래스: next 포인터 제거, ID 내림차순 정렬을 위해 Comparable 구현
    static class Human implements Comparable<Human> {
        int id;
        int version;

        public Human(int id, int version) {
            this.id = id;
            this.version = version;
        }

        // TreeSet이 ID를 기준으로 내림차순 정렬하도록 compareTo 메서드 구현
        @Override
        public int compareTo(Human other) {
            return Integer.compare(other.id, this.id);
        }
    }

    // Team 클래스: head/tail 배열 대신 List<TreeSet<Human>> 사용
    static class Team {
        // 인덱스 = 점수 (1~5), 각 점수에 해당하는 병사들을 TreeSet으로 관리
        List<TreeSet<Human>> scoreBoard;

        public Team() {
            scoreBoard = new ArrayList<>();
            // 0번 인덱스는 더미로 채우고 1~5번 인덱스 사용
            for (int i = 0; i <= 5; i++) {
                scoreBoard.add(new TreeSet<>());
            }
        }
    }

    static final int MAX_SOLDIERS = 100001;
    static Team[] teams;
    static int[] soldierVersion;
    static int[] soldierTeam;

    public void init() {
        teams = new Team[6];
        for (int i = 1; i <= 5; i++) {
            teams[i] = new Team();
        }
        soldierVersion = new int[MAX_SOLDIERS];
        soldierTeam = new int[MAX_SOLDIERS];
    }

    public void hire(int mID, int mTeam, int mScore) {
        int newVersion = ++soldierVersion[mID];
        Human newSoldier = new Human(mID, newVersion);

        // 해당 팀, 해당 점수의 TreeSet에 새로운 Human 객체를 추가
        teams[mTeam].scoreBoard.get(mScore).add(newSoldier);
        soldierTeam[mID] = mTeam;
    }

    public void fire(int mID) {
        // 원본 로직과 동일: TreeSet에서 제거하지 않고 버전만 무효화
        soldierVersion[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore) {
        // 원본 로직과 동일: 새 버전의 Human 객체를 새로 추가
        hire(mID, soldierTeam[mID], mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0) {
            return;
        }

        List<TreeSet<Human>> board = teams[mTeam].scoreBoard;

        if (mChangeScore > 0) { // 점수 상승
            for (int k = 4; k >= 1; k--) {
                int next_score = Math.min(k + mChangeScore, 5);
                if (k == next_score) continue;
                if (board.get(k).isEmpty()) continue;

                // k점 TreeSet의 모든 병사를 next_score의 TreeSet으로 이동
                board.get(next_score).addAll(board.get(k));
                board.get(k).clear();
            }
        } else { // 점수 하락
            for (int k = 2; k <= 5; k++) {
                int next_score = Math.max(k + mChangeScore, 1);
                if (k == next_score) continue;
                if (board.get(k).isEmpty()) continue;

                // k점 TreeSet의 모든 병사를 next_score의 TreeSet으로 이동
                board.get(next_score).addAll(board.get(k));
                board.get(k).clear();
            }
        }
    }

    public int bestSoldier(int mTeam) {
        for (int j = 5; j >= 1; j--) {
            TreeSet<Human> candidates = teams[mTeam].scoreBoard.get(j);
            if (candidates.isEmpty()) continue;

            // TreeSet은 ID 내림차순으로 정렬되어 있음
            // 가장 ID가 높은 병사부터 유효한지(버전이 맞는지) 확인
            for (Human soldier : candidates) {
                if (soldier.version == soldierVersion[soldier.id]) {
                    // 유효한 병사를 찾으면 그 즉시 반환
                    return soldier.id;
                }
            }
        }
        return 0;
    }
}
