import java.util.*;

class UserSolution {
	static class Student {
		int id;
		int score;
		int grade;
		int gender;

		public Student(int id, int score, int grade, int gender) {
			this.id = id;
			this.score = score;
			this.grade = grade;
			this.gender = gender;
		}
	}

	static class Grade {
		TreeSet<Student> male = new TreeSet<>((o1, o2) -> {
			if (o1.score != o2.score) return o1.score - o2.score;
			return o1.id - o2.id;
		});
		TreeSet<Student> female = new TreeSet<>((o1, o2) -> {
			if (o1.score != o2.score) return o1.score - o2.score;
			return o1.id - o2.id;
		});
	}

	static Map<Integer, Student> students;
	static Grade[] grades;

	public void init() {
		students = new HashMap<>();
		grades = new Grade[4];
		for (int i = 1; i <= 3; i++) {
			grades[i] = new Grade();
		}
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		int gender = (mGender[0] == 'm') ? 1 : 0;
		Student s = new Student(mId, mScore, mGrade, gender);
		
		students.put(mId, s);

		if (gender == 1) {
			grades[mGrade].male.add(s);
			return grades[mGrade].male.last().id;
		} else {
			grades[mGrade].female.add(s);
			return grades[mGrade].female.last().id;
		}
	}

	public int remove(int mId) {
		if (!students.containsKey(mId)) {
			return 0;
		}
		
		Student student = students.get(mId);
		students.remove(mId);
		
		int mGrade = student.grade;
		int gender = student.gender;

		if (gender == 1) {
			grades[mGrade].male.remove(student);
			return grades[mGrade].male.isEmpty() ? 0 : grades[mGrade].male.first().id;
		} else {
			grades[mGrade].female.remove(student);
			return grades[mGrade].female.isEmpty() ? 0 : grades[mGrade].female.first().id;
		}
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		int bestId = 0;
		int minScore = Integer.MAX_VALUE;

		Student criteria = new Student(-1, mScore, -1, -1);
		
		List<Integer> gendersToQuery = new ArrayList<>();
		if (mGenderCnt == 2) {
			gendersToQuery.add(0);
			gendersToQuery.add(1);
		} else {
			int gender = (mGender[0][0] == 'm') ? 1 : 0;
			gendersToQuery.add(gender);
		}
		
		for (int i = 0; i < mGradeCnt; i++) {
			int grade = mGrade[i];
			for (int gender : gendersToQuery) {
				TreeSet<Student> targetSet = (gender == 1) ? grades[grade].male : grades[grade].female;
				
				Student candidate = targetSet.ceiling(criteria);
				
				if (candidate != null) {
					if (candidate.score < minScore) {
						minScore = candidate.score;
						bestId = candidate.id;
					} else if (candidate.score == minScore && candidate.id < bestId) {
						bestId = candidate.id;
					}
				}
			}
		}

		return bestId;
	}
}
