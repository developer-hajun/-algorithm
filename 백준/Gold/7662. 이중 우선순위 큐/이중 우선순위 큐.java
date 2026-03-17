import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine().trim());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                String[] co = br.readLine().split(" ");
                char command = co[0].charAt(0);
                int num = Integer.parseInt(co[1]);

                if (command == 'I') {
                    map.merge(num, 1, Integer::sum);
                } else if (map.isEmpty()) {
                    continue;
                } else {
                    int key = (num == 1) ? map.lastKey() : map.firstKey();
                    if (map.get(key) == 1) map.remove(key);
                    else map.merge(key, -1, Integer::sum);
                }
            }

            if (map.isEmpty()) sb.append("EMPTY\n");
            else sb.append(map.lastKey() + " " + map.firstKey() + "\n");
        }
        System.out.print(sb);
    }
}