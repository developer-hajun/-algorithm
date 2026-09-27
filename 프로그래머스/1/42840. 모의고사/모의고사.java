import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] pattern = new int[4][];
        pattern[1] = new int[]{1,2,3,4,5};
        pattern[2] = new int[]{2,1,2,3,2,4,2,5};
        pattern[3] = new int[]{3,3,1,1,2,2,4,4,5,5};

        int[] score = new int[4]; // score[1], score[2], score[3]에 각 사람의 점수 저장

        for (int i = 0; i < answers.length; i++) {
            for (int j = 1; j < pattern.length; j++) {
                int val = i % pattern[j].length;
                if (answers[i] == pattern[j][val]) score[j]++;
            }
        }

        int maxValue = Math.max(score[1], Math.max(score[2], score[3]));

        List<Integer> winners = new ArrayList<>();
        for (int j = 1; j < score.length; j++) {
            if (score[j] == maxValue) {
                winners.add(j);
            }
        }

        int[] answer = new int[winners.size()];
        for (int i = 0; i < winners.size(); i++) {
            answer[i] = winners.get(i);
        }

        return answer;
    }
}