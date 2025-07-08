class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;

        int floor = n / w;
        int div = n % w;

        int[] fl = new int[w];
        for (int i = 0; i < w; i++) fl[i] = floor;

        if (floor % 2 == 0) {
            for (int i = 0; i < div; i++) {
                fl[i]++;
            }
        } else {
            for (int i = w - 1; i >= w - div; i--) {
                fl[i]++;
            }
        }

        int idx = num - 1;
        int row = idx / w;
        int col;
        if (row % 2 == 0) {
            col = idx % w;
        } else {
            col = w - 1 - (idx % w);
        }

        answer = fl[col] - row;

        return answer;
    }
}
