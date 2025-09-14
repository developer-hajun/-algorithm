import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static long[] tree;
    static int n;

    public static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build(node * 2 + 1, mid + 1, end);
            tree[node] = Math.max(tree[node * 2],tree[node * 2 + 1]);
        }
    }

    public static long search(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0; 
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.max(search(node * 2, start, mid, left, right)
        		,search(node * 2 + 1, mid + 1, end, left, right));
    }

    public static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            arr[idx] = val;
            tree[node] = val;
        } else {
            int mid = (
            		start + end) / 2;
            if (idx <= mid) {
                update(node * 2, start, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, end, idx, val);
            }
            tree[node] = Math.max(tree[node * 2],tree[node * 2 + 1]);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];  
        tree = new long[4 * n];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n-m+1; i++) {
            int x = i-m+1;
            int y = i+m-1;
            long sum = search(1, 1, n, Math.min(x, y), Math.max(x, y));
            sb.append(sum).append(" ");
        }
        System.out.print(sb);
    }
}
