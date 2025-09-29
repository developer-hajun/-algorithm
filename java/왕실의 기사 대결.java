import java.util.*;
import java.io.*;

public class Main {
    static class Knight {
        int id, y, x, h, w, k, damage;

        public Knight(int id, int[] make) {
            this.id = id;
            this.y = make[0];
            this.x = make[1];
            this.h = make[2];
            this.w = make[3];
            this.k = make[4];
            this.damage = 0;
        }

        public boolean isDead() {
            return damage >= k;
        }
    }

    static int l, n, q;
    static int[][] map;
    static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Map<Integer, Knight> knights;

    public static List<int[]> can_move_knight(Knight now, int dir) {
        List<int[]> visit = new ArrayList<>();
        if (dir == 0) { 
            int y = now.y - 1;
            if (y < 0) return null;
            for (int i = 0; i < now.w; i++) {
                if (map[y][now.x + i] == 2) return null;
                visit.add(new int[]{y, now.x + i});
            }
        } else if (dir == 1) {
            int x = now.x + now.w;
            if (x >= l) return null;
            for (int i = 0; i < now.h; i++) {
                if (map[now.y + i][x] == 2) return null;
                visit.add(new int[]{now.y + i, x});
            }
        } else if (dir == 2) {
            int y = now.y + now.h;
            if (y >= l) return null;
            for (int i = 0; i < now.w; i++) {
                if (map[y][now.x + i] == 2) return null;
                visit.add(new int[]{y, now.x + i});
            }
        } else if (dir == 3) {
            int x = now.x - 1;
            if (x < 0) return null;
            for (int i = 0; i < now.h; i++) {
                if (map[now.y + i][x] == 2) return null;
                visit.add(new int[]{now.y + i, x});
            }
        }
        return visit;
    }


    public static List<Knight> who_move_knight(List<int[]> find) {
        List<Knight> hitKnights = new ArrayList<>();
        for (Map.Entry<Integer, Knight> entry : knights.entrySet()) {
            Knight k = entry.getValue();
            if (k.isDead()) continue;

            for (int[] pos : find) {
                int y = pos[0];
                int x = pos[1];

                if (y >= k.y && y < k.y + k.h && x >= k.x && x < k.x + k.w) {
                    hitKnights.add(k);
                    break;
                }
            }
        }
        return hitKnights;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map = new int[l][l];
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < l; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        knights = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights.put(i, new Knight(i, new int[]{r, c, h, w, k}));
        }

        for (int i = 1; i <= q; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            if (!knights.containsKey(num) || knights.get(num).isDead()) continue;

            Set<Integer> visited = new HashSet<>();
            Queue<Knight> queue = new ArrayDeque<>();
            queue.add(knights.get(num));
            boolean blocked = false;

            while (!queue.isEmpty()) {
                Knight now = queue.poll();
                if(visited.contains(now.id)) continue;
                visited.add(now.id);
                List<int[]> newArea = can_move_knight(now, dir);
                if (newArea == null) {
                    blocked = true;
                    break;
                }

                List<Knight> overlapped = who_move_knight(newArea);
                for (Knight other : overlapped) {
                    if (!visited.contains(other.id)) {
                        queue.add(other);
                    }
                }
            }
            
            if (blocked) continue;
            
            
            //방문 된것들 움직이고 해당 위치에 데미지 있나 보기
            for(int id : visited) {
            	
            	Knight k  = knights.get(id);
            	k.y += dirs[dir][0];
            	k.x += dirs[dir][1];
            	if(id==num) continue;
            	int count=0;
            	for(int y=k.y;y<k.y+k.h;y++) {
            		for(int x=k.x;x<k.x+k.w;x++) {
            			if(map[y][x]==1) {
            				count++;
            			}
            		}
            	}
            	if(k.damage+count>=k.k) {
            		knights.remove(id);
            	}
            	else {
            		k.damage+=count;
            	}
            }
            
            
        }
        int answer = 0;for (Map.Entry<Integer, Knight> entry : knights.entrySet()) {
        	Knight k = entry.getValue();
        	answer+=entry.getValue().damage;
        }
        System.out.println(answer);


    }
}
