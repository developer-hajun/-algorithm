import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] match;
    static boolean[] visited;
    static List<Integer>[] jobs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 사람을 두 번 복제
        jobs = new ArrayList[2*n];
        for (int i = 0; i < 2*n; i++) jobs[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int work = Integer.parseInt(st.nextToken()) - 1;
                jobs[2*i].add(work);
                jobs[2*i+1].add(work);
            }
        }

        match = new int[m];
        Arrays.fill(match, -1);

        int answer = 0;
        for (int i = 0; i < 2*n; i++) {
            visited = new boolean[m];
            if (dfs(i)) answer++;
        }

        System.out.println(answer);
    }

    public static boolean dfs(int person) {
        for (int job : jobs[person]) {
            if (visited[job]) continue;
            visited[job] = true;

            if (match[job] == -1 || dfs(match[job])) {
                match[job] = person;
                return true;
            }
        }
        return false;
    }
}
