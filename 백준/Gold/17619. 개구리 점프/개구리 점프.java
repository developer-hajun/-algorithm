import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // [시작, 끝, 원본인덱스]
        int[][] value = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            value[i] = new int[] { l, r, i }; // 원본 인덱스 i 저장
        }

        // 시작점 기준 오름차순 정렬
        Arrays.sort(value, (o1, o2) -> o1[0] - o2[0]);

        // 원본 인덱스를 기준으로 그룹 ID를 저장할 배열
        int[] groupIDs = new int[n];
        int groupCount = 0; // 그룹 ID (0부터 시작)

        if (n > 0) {
            int current_left = value[0][0];
            int current_right = value[0][1];
            int original_idx = value[0][2];
            groupIDs[original_idx] = groupCount;

            for (int i = 1; i < n; i++) {
                int[] wood = value[i];
                int n_l = wood[0];
                int n_r = wood[1];
                original_idx = wood[2]; // 현재 나무의 원본 인덱스

                // ★ 오류 2 수정: Math.max 사용
                if (n_l <= current_right) {
                    // 겹치는 경우: 그룹 확장
                    current_right = Math.max(current_right, n_r);
                    groupIDs[original_idx] = groupCount;
                } else {
                    // 겹치지 않는 경우: 새 그룹 시작
                    current_left = n_l;
                    current_right = n_r;
                    groupCount++;
                    groupIDs[original_idx] = groupCount;
                }
            }
        }

        // ★ 비효율 제거: 쿼리 배열을 정렬하지 않음
        // 쿼리를 읽으면서 바로 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            // 쿼리는 1-based index이므로 -1
            int l_idx = Integer.parseInt(st.nextToken()) - 1; 
            int r_idx = Integer.parseInt(st.nextToken()) - 1;

            // ★ 오류 1 수정: groupIDs 배열에서 원본 인덱스로 바로 조회
            if (groupIDs[l_idx] == groupIDs[r_idx]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb);
    }
}