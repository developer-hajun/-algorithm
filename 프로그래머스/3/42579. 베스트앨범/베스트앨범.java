import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> total = new HashMap<>();
        Map<String , TreeMap<Integer,TreeSet<Integer>>> answer = new HashMap<>(); 
        for (int i = 0; i < genres.length; i++){
            if(!total.containsKey(genres[i])){
                total.put(genres[i],0);
                answer.put(genres[i],new TreeMap<>((o1,o2)->{return o2-o1;}));
            }
            total.put(genres[i],total.get(genres[i])+plays[i]);
            TreeMap<Integer,TreeSet<Integer>> now = answer.get(genres[i]);
            if(!now.containsKey(plays[i])) now.put(plays[i],new TreeSet<>((o1,o2)->{return o1-o2;}));
            now.get(plays[i]).add(i);
        }
        TreeMap<Integer,String> e = new TreeMap<>((o1,o2)->{return o2-o1;});
        for(Map.Entry<String,Integer> d : total.entrySet()){
            e.put(d.getValue(),d.getKey());
        }
        List<Integer> ans = new ArrayList<>();
        
        for(Map.Entry<Integer,String> d : e.entrySet()){
            int count=0;
            A:for(Map.Entry<Integer,TreeSet<Integer>> d1 : answer.get(d.getValue()).entrySet()){
                for(int d2 : d1.getValue()){
                    count++;
                    ans.add(d2);
                    if(count==2) break A;
                }
            }
        }
        int[] answer2= new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer2[i] = ans.get(i);
        } 
        
        return answer2;
    
        
    }
}