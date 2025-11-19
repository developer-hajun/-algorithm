import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int orientation(Point A, Point B, Point C) {
        long val = (B.y - A.y) * (C.x - B.x) - (B.x - A.x) * (C.y - B.y);
        
        if (val == 0) return 0;
        return (val > 0) ? 1 : -1; 
    }

    
    public static boolean onSegment(Point A, Point B, Point C) {
        boolean x_check = (C.x <= Math.max(A.x, B.x) && C.x >= Math.min(A.x, B.x));
        boolean y_check = (C.y <= Math.max(A.y, B.y) && C.y >= Math.min(A.y, B.y));
        
        return x_check && y_check;
    }

    
    public static boolean doIntersect(Point P1, Point P2, Point P3, Point P4) {
        int o1 = orientation(P1, P2, P3);
        int o2 = orientation(P1, P2, P4);
        int o3 = orientation(P3, P4, P1);
        int o4 = orientation(P3, P4, P2);


        if (o1 != 0 && o2 != 0 && o3 != 0 && o4 != 0) { 
            if (o1 != o2 && o3 != o4) { 
                return true;
            }
        }
        
        if (o1 == 0 && onSegment(P1, P2, P3)) return true;
        if (o2 == 0 && onSegment(P1, P2, P4)) return true;
        if (o3 == 0 && onSegment(P3, P4, P1)) return true;
        if (o4 == 0 && onSegment(P3, P4, P2)) return true;

        return false;
    }


    public static int find_parents(int[] parents,int value){
        if(parents[value]!=value){
            parents[value]= find_parents(parents,parents[value]);
        }
        return parents[value];
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Point[][] point = new Point[n][2];
        int[] parents = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            point[i][0] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            point[i][1] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            parents[i]=i;
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(find_parents(parents,i)==find_parents(parents,j)) continue;
                

                if(doIntersect(point[i][0],point[i][1],point[j][0],point[j][1])) {
                    int r = find_parents(parents,j);
                    int l = find_parents(parents,i);
                    if(l<r) parents[r]=l;
                    else parents[l]=r;
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            find_parents(parents, i);
        }


        Map<Integer, Integer> groupSizes = new HashMap<>();
        int maxGroupSize = 0;

        for (int i = 0; i < n; i++) {
            int root = parents[i]; 
            groupSizes.put(root, groupSizes.getOrDefault(root, 0) + 1);
        }

        int groupCount = groupSizes.size(); 
        
        for (int size : groupSizes.values()) {
            if (size > maxGroupSize) {
                maxGroupSize = size;
            }
        }
        
        if (n == 0) {
            groupCount = 0;
            maxGroupSize = 0;
        }

        System.out.println(groupCount); 
        System.out.println(maxGroupSize); 
    }
}