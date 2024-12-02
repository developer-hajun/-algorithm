import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

class Pair<K, V,M> {
    private final K start;
    private final V end;
    private final M machine;
    public Pair(K key, V value,M machine) {
        this.start = key;
        this.end = value;
        this.machine = machine;
    }


    public K getStart() {
        return start;
    }

    public V getEnd() {
        return end;
    }
    public M getMachine() {
        return machine;
    }
}

public class job {
    public static void main(String[] args){
        int max = 0;
        List<Integer> time = new ArrayList<>(Arrays.asList(5, 2, 4, 3, 4, 7, 9, 2, 4, 1));
        List<Integer> machine = new ArrayList<>(Arrays.asList(0,0,0,0));
        List<Pair<Integer,Integer,Integer>> answer = new ArrayList<>();
        for(int i :time) {
            int minValue = Collections.min(machine);
            int minIndex = machine.indexOf(minValue);
            if (minValue==0){
                Pair<Integer, Integer, Integer> pair = new Pair<>(minValue, minValue+i-1, minIndex);
                answer.add(pair);
                machine.set(minIndex, minValue+i-1);
                max = Math.max(max,minValue+i);
                continue;
            }
            Pair<Integer, Integer, Integer> pair = new Pair<>(minValue+1, minValue+i, minIndex);
            answer.add(pair);
            machine.set(minIndex, minValue+i);
            max = Math.max(max,minValue+i);
        }
        System.out.println(max);
        String[][] matrix = new String[4][];
        matrix[0] = new String[13];
        matrix[1] = new String[13];
        matrix[2] = new String[13];
        matrix[3] = new String[13];

        int now=1;
        for(Pair<Integer,Integer,Integer> pair : answer) {
            for(int i = pair.getStart();i<=pair.getEnd();i++) {
                matrix[pair.getMachine()][i] = "t"+now;
            }
            now+=1;
        }
        System.out.print("   "); // 첫 번째 열은 비워두기
        for (int i = 0; i < 13; i++) {
            System.out.printf("%-4d", i); // 4칸 폭으로 정렬
        }
        System.out.println();

        now=4;
        // 행 출력
        for (int rowIndex = matrix.length-1; rowIndex >=0; rowIndex--) {
            System.out.printf("%-2d ", now); // 행 번호 출력
            now-=1;
            for (String cell : matrix[rowIndex]) {
                System.out.printf("%-4s", (cell == null ? " " : cell)); // null인 경우 빈칸 출력
            }
            System.out.println();
        }
    }
}