import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (String now : operations) {
            String[] co = now.split(" ");
            char command = co[0].charAt(0);
            int num = Integer.parseInt(co[1]);

            if (command == 'I') {
                map.merge(num, 1, Integer::sum);
            } else if (map.isEmpty()) {
                continue;
            } else {
                int key = (num == 1) ? map.lastKey() : map.firstKey();
                if (map.get(key) == 1) map.remove(key);
                else map.put(key,map.get(key)-1);
            }
        }
        if (map.isEmpty()) return new int[]{0, 0};
        return new int[]{map.lastKey(), map.firstKey()};
    }
}