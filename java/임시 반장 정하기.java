import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // arr[학년][반번호] = 해당 반에 속한 학생 번호 집합
        List<List<Set<Integer>>> arr = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            arr.add(new ArrayList<>());
            for(int j = 0; j <= 9; j++){
                arr.get(i).add(new HashSet<>());
            }
        }

        // 입력 받기 (학생별로 5년간 반 번호 입력)
        for(int student = 1; student <= n; student++){
            for(int grade = 1; grade <= 5; grade++){
                int c = sc.nextInt();  // 반드시 변수에 저장!
                arr.get(grade).get(c).add(student);
            }
        }

        // 각 학생별로 같은 반이었던 사람들 집합
        List<Set<Integer>> answer = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            answer.add(new HashSet<>());
        }

        for(int grade = 1; grade <= 5; grade++){
            for(int room = 1; room <= 9; room++){
                Set<Integer> now = arr.get(grade).get(room);
                if(now.size() <= 1) continue;  // 혼자면 의미 없음
                for(int s : now){
                    answer.get(s).addAll(now);
                    answer.get(s).remove(s);  // 자기 자신 제외
                }
            }
        }

        // 최대 같은 반 사람이 가장 많은 학생 찾기
        int maxCount = -1;
        int num = 0;
        for(int i = 1; i <= n; i++){
            int count = answer.get(i).size();
            if(count > maxCount){
                maxCount = count;
                num = i;
            }
        }

        System.out.println(num);
    }
}
