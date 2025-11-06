import java.util.*;
import java.io.*;

class Main {

    // 소의 증언을 저장할 간단한 클래스
    static class Constraint {
        char type; // 'L' 또는 'G'
        int p;     // 위치

        public Constraint(char type, int p) {
            this.type = type;
            this.p = p;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 1. 모든 증언을 저장
        Constraint[] constraints = new Constraint[n];
        // 2. Bessie의 후보 위치들을 저장
        int[] locations = new int[n]; 

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int p = Integer.parseInt(st.nextToken());
            
            constraints[i] = new Constraint(type, p);
            locations[i] = p; 
        }

        int minLies = n; 

        for (int i = 0; i < n; i++) {
            int candidateX = locations[i]; 
            int currentLies = 0;


            for (int j = 0; j < n; j++) {
                Constraint c = constraints[j];
                
                if (c.type == 'L') {
                    if (candidateX > c.p) {
                        currentLies++;
                    }
                } else { // c.type == 'G'
                    if (candidateX < c.p) {
                        currentLies++;
                    }
                }
            }

            minLies = Math.min(minLies, currentLies);
        }

        System.out.print(minLies);
    }
}