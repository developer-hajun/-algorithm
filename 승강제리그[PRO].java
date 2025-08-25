import java.util.*;

class UserSolution {
	static class human {
		int id;
		int ability;
		public human(int a, int b) {
			id = a;
			ability = b;
		}
	}

	static class League {
		int id;
		TreeSet<human> set;
		public League(int i) {
			id = i;
			set = new TreeSet<>((o1, o2) -> {
				if (o1.ability != o2.ability) return o1.ability - o2.ability;
				return o2.id - o1.id;
			});
		}
	}

	static League[] leagues;
	static human[] humans;

	void init(int N, int L, int mAbility[]) {
		leagues = new League[L];
		humans = new human[N];
		for (int i = 0; i < L; i++) leagues[i] = new League(i);
		for (int i = 0; i < N; i++) {
			humans[i] = new human(i, mAbility[i]);
			leagues[i / (N / L)].set.add(humans[i]);
		}
	}

	int move() {
		human[][] change = new human[leagues.length - 1][2];
		int answer = 0;
		for (int i = 1; i < leagues.length; i++) {
			change[i - 1][0] = leagues[i - 1].set.pollFirst();
			change[i - 1][1] = leagues[i].set.pollLast();
			answer += change[i - 1][0].id;
			answer += change[i - 1][1].id; 
		}
		for (int i = 1; i < leagues.length; i++) {
			leagues[i - 1].set.add(change[i - 1][1]);
			leagues[i].set.add(change[i - 1][0]);
		}
		return answer;
	}

	int trade() {
		human[][] change = new human[leagues.length - 1][2];
		int answer = 0;
		for (int i = 1; i < leagues.length; i++) {
			change[i - 1][0] = find_mid(leagues[i - 1]);
			change[i - 1][1] = leagues[i].set.pollLast();
			answer += change[i - 1][0].id; 
			answer += change[i - 1][1].id; 
		}
		for (int i = 1; i < leagues.length; i++) {
			leagues[i - 1].set.add(change[i - 1][1]);
			leagues[i].set.add(change[i - 1][0]);
		}
		return answer;
	}

	human find_mid(League league) {
		int size = league.set.size();
		int midIndex = size / 2;
		Iterator<human> it = league.set.iterator();
		human mid = null;
		for (int i = 0; i <= midIndex && it.hasNext(); i++) mid = it.next();
		league.set.remove(mid);
		return mid;
	}
}
