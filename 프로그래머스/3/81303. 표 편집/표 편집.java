import java.util.*;

public class Solution {

    static class Node {
        int prev, next;
        boolean isDeleted;

        Node(int prev, int next) {
            this.prev = prev;
            this.next = next;
            this.isDeleted = false;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i - 1, i + 1);
        }
        nodes[n - 1].next = -1; // 마지막 노드의 다음은 없음

        Stack<Integer> deletedStack = new Stack<>();

        for (String c : cmd) {
            char op = c.charAt(0);

            if (op == 'U') {
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) {
                    k = nodes[k].prev;
                }
            } else if (op == 'D') {
                int x = Integer.parseInt(c.substring(2));
                for (int i = 0; i < x; i++) {
                    k = nodes[k].next;
                }
            } else if (op == 'C') {
                nodes[k].isDeleted = true;
                deletedStack.push(k);

                // 연결 끊기
                int prev = nodes[k].prev;
                int next = nodes[k].next;

                if (prev != -1) nodes[prev].next = next;
                if (next != -1) nodes[next].prev = prev;

                // 커서 이동
                k = (next != -1) ? next : prev;

            } else if (op == 'Z') {
                int restored = deletedStack.pop();
                nodes[restored].isDeleted = false;

                // 연결 복원
                int prev = nodes[restored].prev;
                int next = nodes[restored].next;

                if (prev != -1) nodes[prev].next = restored;
                if (next != -1) nodes[next].prev = restored;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nodes[i].isDeleted ? 'X' : 'O');
        }

        return sb.toString();
    }
}
