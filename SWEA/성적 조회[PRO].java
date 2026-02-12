

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class UserSolution {
	
	static class Student{
		int id;
		int grade;
		int gender;
		int score;
		boolean delete;
		
		public Student(int mid,int mGrade,int gender,int score) {
			id = mid;
			grade = mGrade;
			this.gender = gender;
			this.score = score;
			delete= false;
		}
		public Student(int score) {
			this.score=score;
			this.id = -1;
		}
	}
	
	Map<Integer,Student> StudentById = new HashMap<>();
	TreeSet<Student>[][] Students =  new TreeSet[4][2];
	
	public int change_Gender(String now) {
		if(now.equals("female")) {
			return 0;
		}
		return 1;
	}
	
	public void init() {
		StudentById.clear();
		for(int grade=1;grade<4;grade++) {
			for(int gender=0;gender<2;gender++) {
				Students[grade][gender] = new TreeSet<>((o1,o2)->{
					if(o1.score==o2.score) return o1.id-o2.id;
					return o1.score-o2.score;
				});
			}
		}
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		int gender = change_Gender(new String(mGender).trim());
		
		Student now = new Student(mId,mGrade,gender,mScore);
		
		StudentById.put(mId, now);
		Students[mGrade][gender].add(now);
		
		while(Students[mGrade][gender].last().delete) {
			Students[mGrade][gender].pollLast();
		}
		return Students[mGrade][gender].last().id;
		
	}

	public int remove(int mId) {
		if(!StudentById.containsKey(mId)) return 0;
		
		Student now = StudentById.get(mId);
		
		if(now.delete) return 0;
		now.delete = true;
		int grade = now.grade;
		int gender = now.gender;
		
		while(!Students[grade][gender].isEmpty()) {
			Student find = Students[grade][gender].first();
			if(find.delete) Students[grade][gender].pollFirst();
			else return find.id;
		}
		return 0;
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
	    Student find = null;

	    for (int i = 0; i < mGradeCnt; i++) {
	        int grade = mGrade[i];

	        for (int j = 0; j < mGenderCnt; j++) {
	            int gender = change_Gender(new String(mGender[j]).trim());

	            TreeSet<Student> now = Students[grade][gender];

	            Student higherStudent = now.higher(new Student(mScore));

	            while (higherStudent != null && higherStudent.delete) {
	            	now.remove(higherStudent);
	                higherStudent = now.higher(new Student(mScore)); 
	            }

	            if (higherStudent != null) {
	                if (find == null) {
	                    find = higherStudent;
	                } else if (find.score > higherStudent.score) {
	                    find = higherStudent;
	                } else if (find.score == higherStudent.score && find.id > higherStudent.id) {
	                    find = higherStudent;
	                }
	            }
	        }
	    }
	    
	    
	    if (find == null) {
	        return 0;
	    }
	    return find.id;
	}


}

